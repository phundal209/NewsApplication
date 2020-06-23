package com.news.news.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.news.media.api.ImageProvider
import com.news.news.api.NewsArticle
import com.news.news.presentation.databinding.NewsViewItemBinding

class NewsItemViewHolder(itemView: NewsViewItemBinding,
            private val imageProvider: ImageProvider) : RecyclerView.ViewHolder(itemView.root) {
    private val headlineText = itemView.headlineText
    private val descriptionText = itemView.descriptionText
    private val contentText = itemView.contentText
    private val authorText = itemView.authorText
    private val publishedAtText = itemView.publishedAtText
    private val newsImage = itemView.newsImage
    fun bind(article: NewsArticle) {
        headlineText.text = article.title
        descriptionText.text = article.description
        contentText.text = article.content
        authorText.text = article.author
        publishedAtText.text = article.publishedAt
        imageProvider.load(article.urlToImage, newsImage)
    }
}