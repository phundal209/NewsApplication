package com.news.news.presentation

import androidx.recyclerview.widget.DiffUtil
import com.news.news.api.NewsArticle

object NewsDiffUtil {
    val DIFF_CALLBACK : DiffUtil.ItemCallback<NewsArticle> =
        object : DiffUtil.ItemCallback<NewsArticle>() {
            override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
                return oldItem == newItem
            }
        }
}