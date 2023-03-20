package com.myappordevos.testflow.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.myappordevos.testflow.presentation.adapters.Adapter
import com.myappordevos.testflow.presentation.viewmodels.MainViewModel
import com.myappordevos.testflow.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainBind by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBind.root)
        val adapter = Adapter()
        mainBind.rcView.adapter = adapter
        mainBind.rcView.layoutManager = LinearLayoutManager(this)
        mainViewModel.sendReq()
        lifecycleScope.launch {
            mainViewModel.resultApi.collect{
                when(it) {
                    is StateApi.Empty -> Log.e("result","empty")
                    is StateApi.ErrorLoading -> Log.e("result","ErrorLoading ${it.message}")
                    is StateApi.Loading -> Log.e("result","Loading")
                    is StateApi.Finished -> adapter.myListItem = it.result.competitions
                }
            }
        }
    }
}