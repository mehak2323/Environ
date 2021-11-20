package com.example.environ.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.environ.R
import com.example.environ.tasks.TaskItemClicked
import com.example.environ.tasks.TaskListAdapter

class TaskFragment : Fragment(), TaskItemClicked {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //RecyclerView Part ...
        //Add layout manager to recyclerview
        val recyclerView = getView()?.findViewById<RecyclerView>(R.id.fragment_task_recyclerView)
        if (recyclerView != null) {
            recyclerView.layoutManager = LinearLayoutManager(activity)
        }
        //define adapter and pass list of strings to it
        val items = fetchData()
        val adapter = TaskListAdapter(items, this)
        //Add adapter to recyclerView
        if (recyclerView != null) {
            recyclerView.adapter = adapter
        }
        //....RecyclerView part end

    }

    //List of tasks text to be displayed
    private fun fetchData(): ArrayList<String> {
        val list = ArrayList<String>()
        list += resources.getStringArray(R.array.tasks)
        return list
    }

    //RecyclerView item OnClick method:
    override fun onItemClicked(item: String) {
        Toast.makeText(activity, "Task description will be added soon: $item", Toast.LENGTH_LONG).show()
    }
}