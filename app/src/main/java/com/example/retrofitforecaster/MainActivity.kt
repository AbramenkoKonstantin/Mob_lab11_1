package com.example.retrofitforecaster

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var mService: RetrofitServices
    private val adapter = Adapter()
    private lateinit var viewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.rView)

        viewModel = ViewModelProviders.of(this)[MyViewModel::class.java]
        mService = Common.retrofitService
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        Timber.plant(Timber.DebugTree())
        Timber.d(viewModel.getWeatherData().toString())
        adapter.submitList(viewModel.getWeatherData())

    }
}