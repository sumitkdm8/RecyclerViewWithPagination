package com.example.rvdemo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rvdemo.R
import com.example.rvdemo.models.Students

class StudentAdapter(private var studentList: MutableList<Students>) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val name: TextView = viewItem.findViewById(R.id.tv_name)
//        val rollNo: TextView? = viewItem.findViewById(R.id.tv_roll_no) // Optional rollNo TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.name.text = studentList[position].name
        holder.name.setTextColor(studentList[position].color)
//        holder.rollNo?.text = studentList[position].rollNo // Set rollNo if it exists
    }

    fun addItems(newItems: MutableList<Students>) {
        val startPosition = studentList.size
        studentList.addAll(newItems)
        notifyItemRangeInserted(startPosition, newItems.size)
    }
}
