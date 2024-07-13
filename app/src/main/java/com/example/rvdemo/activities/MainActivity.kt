package com.example.rvdemo.activities

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rvdemo.R
import com.example.rvdemo.adapters.StudentAdapter
import com.example.rvdemo.models.Students


class MainActivity : AppCompatActivity() {
    private lateinit var adapter: StudentAdapter
    private var isLoading = false
    private var items = mutableListOf<Students>()
    private var currentPage = 0
    private val pageSize = 50
    private val originalList = MutableList(1000) {
        Students(
            "Student ${it + 1}",
            "${it + 1}",
            if (it % 2 == 0) Color.RED else Color.BLACK
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_main) // Corrected layout name

        val recyclerView = findViewById<RecyclerView>(R.id.rv_student) // Explicit type casting
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = StudentAdapter(items)
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                if (!isLoading && layoutManager.findLastCompletelyVisibleItemPosition() == items.size - 1) {
                    // Load more data here
                    loadMoreItems()
                }
            }
        })

        // Initial load
        loadMoreItems()
    }

    private fun loadMoreItems() {
        isLoading = true
        currentPage++

        // Simulate network delay
        Handler(Looper.getMainLooper()).postDelayed({
            val newItems = fetchData(currentPage, pageSize)
            adapter.addItems(newItems)
            isLoading = false
        }, 1500)
    }

    private fun fetchData(page: Int, size: Int): MutableList<Students> {
        // Calculate the start and end index for pagination
        val start = (page - 1) * size
        val end = (start + size).coerceAtMost(originalList.size) // Ensure end does not exceed the original list size

        // Return the sublist based on pagination parameters
        return originalList.subList(start, end).toMutableList()
    }
}
