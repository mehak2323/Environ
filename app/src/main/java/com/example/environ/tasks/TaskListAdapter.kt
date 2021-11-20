package com.example.environ.tasks

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.environ.R

class TaskListAdapter(private val items: ArrayList<String>, private val listener: TaskItemClicked):
    RecyclerView.Adapter<TaskViewHolder>() {

    //Below three functions needed/ suggested on default for adapter class
    //1: called every time new view appears on screen. It returns a NewsViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)

        //we get position of item in onClickListener block in OnItemClicked fun using view holder
        val viewHolder = TaskViewHolder(view)

        //now adding on click functionality to the item
        view.setOnClickListener {
            //the item at given position(from view holder) will be passed to onItemClicked fun
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }
    //2: binds data into the holder
    override fun onBindViewHolder(holder: TaskViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem

        //On Click Listener for recycler view objects, if made in adapter
//        holder.itemView.setOnClickListener(object: View.OnClickListener{
//
//            override fun onClick(v: View?) {
//                //Fragment change
//                val activity= v!!.context as AppCompatActivity
//
//                val task2DescriptionFragment = Task2DescriptionFragment(position)
//                activity.supportFragmentManager.beginTransaction().replace(R.id.rec, task2DescriptionFragment ).addToBackStack(null).commit()
//
//                Toast.makeText(activity, "Task description will be added soon", Toast.LENGTH_LONG).show()
//            }
//        })

    }
    //3: called once to know total number of views
    override fun getItemCount(): Int {
        return items.size
    }

}

//View Holder class
class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val titleView: TextView = itemView.findViewById(R.id.item_task_title)
}

//Interface to handle on click in main fragment
interface TaskItemClicked{
    fun onItemClicked(item:String)
}