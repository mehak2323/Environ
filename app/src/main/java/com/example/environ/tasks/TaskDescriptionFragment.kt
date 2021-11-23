package com.example.environ.tasks

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.environ.databinding.FragmentTaskDescriptionBinding
import kotlinx.android.synthetic.main.fragment_task_description.*


class TaskDescriptionFragment(private val position: Int) : Fragment(), DataTaskDescription {

    private lateinit var  taskDescBinding: FragmentTaskDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Default: Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_task_description, container, false)

        //Data binding added
        taskDescBinding = FragmentTaskDescriptionBinding.inflate(inflater, container, false)
        return taskDescBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Add/change text data here:
        val posNum = position +1

        fragment_task_desc_task_num.text= "Task $posNum"
        fragment_task_desc_title.text= getTitle(posNum)
        fragment_task_desc_description.text = getDescription(posNum)

        //Share button listener
        fragment_task_desc_share.setOnClickListener {
            shareTask(getTitle(posNum))
        }

        //Back button listener
        fragment_task_desc_back.setOnClickListener {
            fragmentManager?.popBackStack()
        }

        fragment_task_desc_completed_button.setOnClickListener {
            Toast.makeText(activity,
                "Thank You for completing this task! Your points are added in your profile. We appreciate your honesty."
                ,Toast.LENGTH_LONG).show()
        }
    }

    private fun shareTask(taskTitle: String) {
        //intents used to change process
        val intent= Intent(Intent.ACTION_SEND)
        //defining what type of thing we have, to suggest those apps for sharing, like pdf, text, image, etc
        intent.type = "text/plain"
        //extra text while sharing
        intent.putExtra(Intent.EXTRA_TEXT, "Hi, would you like to try this task: $taskTitle")
        //gives us sharing app options
        val chooser = Intent.createChooser(intent, "Share this task using: ")
        startActivity(chooser)
    }

}
