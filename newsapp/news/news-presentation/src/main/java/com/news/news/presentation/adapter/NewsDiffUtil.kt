package com.news.news.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.news.news.api.NewsArticle

class NewsDiffUtil(private val oldNewsArticles: List<NewsArticle>,
                   private val newNewsArticles: List<NewsArticle>): DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldNewsArticles[oldItemPosition].title == newNewsArticles[newItemPosition].title
    }

    override fun getOldListSize(): Int =
        oldNewsArticles.size

    override fun getNewListSize(): Int =
        newNewsArticles.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldNewsArticles[oldItemPosition]
        val newItem = newNewsArticles[newItemPosition]

        return oldItem.title == newItem.title
    }
}