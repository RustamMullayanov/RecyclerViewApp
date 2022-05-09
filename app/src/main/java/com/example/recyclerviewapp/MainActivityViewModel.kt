package com.example.recyclerviewapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    val employees = MutableLiveData(Employee.getMockEmployees())

    fun likeEmployee(position: Int) {
        employees.value?.get(position)?.isLiked = !(employees.value?.get(position)?.isLiked)!!
    }
}