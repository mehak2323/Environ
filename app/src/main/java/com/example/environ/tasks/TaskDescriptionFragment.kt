package com.example.environ.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.environ.R
import com.example.environ.databinding.FragmentTaskDescriptionBinding
import kotlinx.android.synthetic.main.fragment_task_description.*

//import com.example.environ.tasks.DataTaskDescription

class TaskDescriptionFragment(private val position: Int) : Fragment() {

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

        //Add/change text data here:
        var posNum = position +1
        fragment_task_desc_task_num.text= "Task $posNum"

    }
}

//interface DataTaskDescription() {
//
//    fun getTitle(){}
//    fun getDescription(){}
//    fun getPoints(){}
//}