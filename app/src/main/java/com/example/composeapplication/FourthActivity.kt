package com.example.composeapplication

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.composeapplication.paging3.Repo
import com.example.composeapplication.paging3.RepoAdapter
import com.example.composeapplication.paging3.Repository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

internal class FourthViewModel: ViewModel() {
    fun getPagingData(): Flow<PagingData<Repo>>{
        return Repository.getPagingData().cachedIn(viewModelScope)
    }
}

@AndroidEntryPoint
class FourthActivity : AppCompatActivity(){
    private val viewModel by lazy { ViewModelProvider(this)[FourthViewModel::class.java] }

    private val repoAdapter = RepoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "FourthActivity"
        setContentView(R.layout.fourth_activity)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = repoAdapter
        lifecycleScope.launch {
            viewModel.getPagingData().collect { pagingData ->
                repoAdapter.submitData(pagingData)
            }
        }
        repoAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    progressBar.visibility = View.INVISIBLE
                    recyclerView.visibility = View.VISIBLE
                }
                is LoadState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.INVISIBLE
                }
                is LoadState.Error -> {
                    val state = it.refresh as LoadState.Error
                    progressBar.visibility = View.INVISIBLE
                    Toast.makeText(this, "Load Error: ${state.error.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}