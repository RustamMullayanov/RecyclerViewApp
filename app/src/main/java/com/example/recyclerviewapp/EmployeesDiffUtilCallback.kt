package com.example.recyclerviewapp

import androidx.recyclerview.widget.DiffUtil

class EmployeesDiffUtilCallback(
    private val oldList: List<Employee>,
    private val newList: List<Employee>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[oldItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldList[oldItemPosition]
        val new = newList[oldItemPosition]
        return old.id == new.id && (old.isLiked == new.isLiked)
    }
}