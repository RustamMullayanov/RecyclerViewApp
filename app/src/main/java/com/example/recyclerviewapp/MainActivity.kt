package com.example.recyclerviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var employeeAdapter: EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val itemDecoration = DividerItemDecoration(
            recyclerView.context,
            GridLayoutManager.VERTICAL
        )

        val itemDecoration2 = DividerItemDecoration(
            recyclerView.context,
            GridLayoutManager.HORIZONTAL
        )
        recyclerView.addItemDecoration(itemDecoration)
        recyclerView.addItemDecoration(itemDecoration2)

        employeeAdapter = EmployeeAdapter(viewModel::likeEmployee)

        recyclerView.adapter = employeeAdapter

        viewModel.employees.observe(this) {
            employeeAdapter.reload(it)
        }
    }
}