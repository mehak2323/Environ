package com.example.environ.dashboard

import androidx.appcompat.app.AppCompatActivity
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import org.tensorflow.lite.support.common.TensorProcessor
import android.graphics.Bitmap
import android.widget.TextView
import android.os.Bundle
import android.content.Intent
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.ops.ResizeWithCropOrPadOp
import org.tensorflow.lite.support.image.ops.ResizeOp
import kotlin.Throws
import android.app.Activity
//import android.content.res.AssetFileDescriptor
import android.net.Uri
import org.tensorflow.lite.support.common.TensorOperator
import org.tensorflow.lite.support.common.ops.NormalizeOp

import org.tensorflow.lite.support.common.FileUtil
import org.tensorflow.lite.support.label.TensorLabel
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.example.environ.R
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.io.IOException
import java.lang.Exception
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import java.util.*

class SortMeActivity : AppCompatActivity() {
    protected var tflite: Interpreter? = null
    private val tfliteModel: MappedByteBuffer? = null
    private var inputImageBuffer: TensorImage? = null
    private var imageSizeX = 0
    private var imageSizeY = 0
    lateinit var outputProbabilityBuffer: TensorBuffer
    private var probabilityProcessor: TensorProcessor? = null
    private var bitmap: Bitmap? = null
    lateinit var labels: List<String>
    var imageView: ImageView? = null
    var imageuri: Uri? = null
    var buclassify: Button? = null
    var classitext: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sort_me)
        imageView = findViewById<View>(R.id.image) as ImageView
        buclassify = findViewById<View>(R.id.classify) as Button
        classitext = findViewById<View>(R.id.classifytext) as TextView
        imageView!!.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 12)
        }
        try {
            tflite = Interpreter(loadmodelfile(this))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        buclassify!!.setOnClickListener {
            val imageTensorIndex = 0
            val imageShape =
                tflite!!.getInputTensor(imageTensorIndex).shape() // {1, height, width, 3}
            imageSizeY = imageShape[1]
            imageSizeX = imageShape[2]
            val imageDataType = tflite!!.getInputTensor(imageTensorIndex).dataType()
            val probabilityTensorIndex = 0
            val probabilityShape =
                tflite!!.getOutputTensor(probabilityTensorIndex).shape() // {1, NUM_CLASSES}
            val probabilityDataType = tflite!!.getOutputTensor(probabilityTensorIndex).dataType()
            inputImageBuffer = TensorImage(imageDataType)
            outputProbabilityBuffer =
                TensorBuffer.createFixedSize(probabilityShape, probabilityDataType)
            probabilityProcessor = TensorProcessor.Builder().add(postprocessNormalizeOp).build()
            inputImageBuffer = bitmap?.let { it1 -> loadImage(it1) }
            tflite!!.run(inputImageBuffer!!.buffer, outputProbabilityBuffer.getBuffer().rewind())
            showresult()
        }
    }

    private fun loadImage(bitmap: Bitmap): TensorImage {
        // Loads bitmap into a TensorImage.
        inputImageBuffer!!.load(bitmap)

        // Creates processor for the TensorImage.
        val cropSize = Math.min(bitmap!!.width, bitmap.height)
        // TODO(b/143564309): Fuse ops inside ImageProcessor.
        val imageProcessor = ImageProcessor.Builder()
            .add(ResizeWithCropOrPadOp(cropSize, cropSize))
            .add(ResizeOp(imageSizeX, imageSizeY, ResizeOp.ResizeMethod.NEAREST_NEIGHBOR))
            .add(preprocessNormalizeOp)
            .build()
        return imageProcessor.process(inputImageBuffer)
    }

    @Throws(IOException::class)
    private fun loadmodelfile(activity: Activity): MappedByteBuffer {
        val fileDescriptor = activity.assets.openFd("yourmodel.tflite")
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startoffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startoffset, declaredLength)
    }

    private val preprocessNormalizeOp: TensorOperator
        private get() = NormalizeOp(IMAGE_MEAN, IMAGE_STD)
    private val postprocessNormalizeOp: TensorOperator
        private get() = NormalizeOp(PROBABILITY_MEAN, PROBABILITY_STD)

    private fun showresult() {
        try {
            labels = FileUtil.loadLabels(this, "labels.txt")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val labeledProbability =
            TensorLabel(labels, probabilityProcessor!!.process(outputProbabilityBuffer))
                .mapWithFloatValue
        val maxValueInMap = Collections.max(labeledProbability.values)
        for ((key, value) in labeledProbability) {
            if (value == maxValueInMap) {
                classitext!!.text = key
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 12 && resultCode == RESULT_OK && data != null) {
            imageuri = data.data
            try {
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageuri)
                imageView!!.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        private const val IMAGE_MEAN = 0.0f
        private const val IMAGE_STD = 1.0f
        private const val PROBABILITY_MEAN = 0.0f
        private const val PROBABILITY_STD = 255.0f
    }
}

/*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.environ.R

class SortMeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sort_me)
    }
}
*/