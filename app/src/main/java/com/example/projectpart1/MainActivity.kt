package com.example.projectpart1

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.GridLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), SelectListener{
    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter

    private val dataLoaderViewModel: DataLoaderViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        val dialog = ProgressDialog(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dialog.setTitle("Loading...")
        dialog.show()
        dataLoaderViewModel.loadNews()
        dataLoaderViewModel.newsLiveData.observe(this) { newsList ->
            listener.onFetchNews(newsList)
        }
    }

    private val listener = object : OnFetchNewsListener<ArticleResponse> {
        override fun onFetchNews(newsList: List<ArticleResponse>) {
            showNews(newsList as ArrayList<ArticleResponse>)
        }

        override fun onError(message: String) {
            val text = "Something went wrong"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
        }
    }
    
    private fun showNews(newsList : ArrayList<ArticleResponse>) {
        recyclerView = findViewById(R.id.recycler_main)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        myAdapter = MyAdapter(newsList)
        
    }

    override fun onNewsClicked(news: ArticleResponse) {
        startActivity(Intent(Intent(this, DetailsActivity::class.java))
            .putExtra("data", news.content))
    }
}