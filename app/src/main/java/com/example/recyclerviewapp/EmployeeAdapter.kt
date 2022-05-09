package com.example.recyclerviewapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class EmployeeAdapter(
    private val clickedLike: (Int) -> Unit
) :
    RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    private val employees = mutableListOf<Employee>()

    inner class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val employeeCard: ConstraintLayout = itemView.findViewById(R.id.employees_list_item)
        val photoImageView: ImageView = itemView.findViewById(R.id.photo)
        val fullNameTextView: TextView = itemView.findViewById(R.id.full_name)
        val departmentTextView: TextView = itemView.findViewById(R.id.department)
        val likeButton: ImageView = itemView.findViewById(R.id.like_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.employees_list_item, parent, false)
        return EmployeeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee = employees[position]
        with(holder) {
            fullNameTextView.text = employee.fullName
            departmentTextView.text = employee.departament

            likeButton.visibility = if (employee.isLiked) View.VISIBLE else View.INVISIBLE

            Glide.with(photoImageView.context)
                .load(employee.photoUrl)
                .centerCrop()
                .into(photoImageView)

            employeeCard.setOnClickListener {
                clickedLike(position)
                likeButton.visibility = if (employee.isLiked) View.VISIBLE else View.INVISIBLE
            }
        }
    }

    override fun getItemCount(): Int {
        return employees.size
    }

    fun reload(data: List<Employee>) {
        val diffUtil = EmployeesDiffUtilCallback(employees, data)
        val result = DiffUtil.calculateDiff(diffUtil)
        employees.clear()
        employees.addAll(data)
        result.dispatchUpdatesTo(this)
    }
}