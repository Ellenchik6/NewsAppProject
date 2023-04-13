package com.example.projectpart1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter(private var newsList : ArrayList<ArticleResponse>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    lateinit var listener : SelectListener
    val articleList: List<ArticleResponse> = newsList.map { news ->
        ArticleResponse(
            title = news.title,
            description = news.description,
            author = news.author,
            content = news.content,
            publishedAt = news.publishedAt,
            source = news.source,
            url = news.url,
            urlToImage = news.urlToImage

        )
    }
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.headline_list_items, parent, false))
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.textTile.text = currentItem.title
        holder.textSource.text = currentItem.source.toString()

        if(articleList[position].urlToImage != null){
            Picasso.get().load(articleList[position].urlToImage).into(holder.img_headline)
        }

        holder.cardView.setOnClickListener {
            listener.onNewsClicked(newsList[position])
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textTile : TextView = itemView.findViewById(R.id.text_title)
        val textSource : TextView = itemView.findViewById(R.id.text_source)
        val img_headline : ImageView = itemView.findViewById(R.id.img_headline)
        val cardView : CardView = itemView.findViewById(R.id.main_container)
    }
}