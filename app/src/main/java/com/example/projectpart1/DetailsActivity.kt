package com.example.projectpart1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    lateinit var newsResponse : ArticleResponse
    val txtTitle: TextView = findViewById(R.id.text_title)
    val txtAuthor: TextView = findViewById(R.id.text_detail_author)
    val txtTime: TextView = findViewById(R.id.text_detail_time)
    val txtDetail: TextView = findViewById(R.id.text_detail_detail)
    val txtContent: TextView = findViewById(R.id.text_detail_content)
    val img_news: ImageView = findViewById(R.id.img_detail_news)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        newsResponse = getIntent().getSerializableExtra("data") as ArticleResponse
        txtTitle.setText(newsResponse.title)
        txtAuthor.setText(newsResponse.author)
        txtTime.setText(newsResponse.publishedAt)
        txtDetail.setText(newsResponse.description)
        txtContent.setText(newsResponse.content)
        Picasso.get().load(newsResponse.urlToImage).into(img_news)

    }
}