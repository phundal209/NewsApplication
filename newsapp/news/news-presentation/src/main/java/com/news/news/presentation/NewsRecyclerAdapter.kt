package com.news.news.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.news.media.api.ImageProvider
import com.news.news.api.NewsArticle
import com.news.news.presentation.databinding.NewsViewItemBinding

class NewsRecyclerAdapter(private val imageProvider: ImageProvider,
                          private val newsItemCallback: NewsItemCallback)
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
        holder.itemView.setOnClickListener {
            newsItemCallback.onNewsArticleClicked(articleUrl = article.url)
        }
    }

    fun setData(articles: List<NewsArticle>) {
        val diffCallback = NewsDiffUtil(newsArticles, articles)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        newsArticles.clear()
        newsArticles.addAll(articles)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int =
        newsArticles.size
}