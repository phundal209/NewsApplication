package com.news.news.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.news.media.api.ImageProvider
import com.news.news.api.NewsArticle
import com.news.news.presentation.databinding.NewsViewItemBinding

class NewsRecyclerAdapter(private val context: Context,
                          private val imageProvider: ImageProvider)
    : RecyclerView.Adapter<NewsItemViewHolder>() {
    private var newsArticles: MutableList<NewsArticle> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val newsViewBinding: NewsViewItemBinding = NewsViewItemBinding.inflate(layoutInflater, parent, false)
        return NewsItemViewHolder(newsViewBinding, imageProvider)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val article = newsArticles[position]
        holder.bind(article)
    }

    fun setData(article: List<NewsArticle>) {
        newsArticles.clear()
        newsArticles.addAll(article)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int =
        newsArticles.size
}