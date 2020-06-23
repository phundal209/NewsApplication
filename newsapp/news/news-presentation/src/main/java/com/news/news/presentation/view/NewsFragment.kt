package com.news.news.presentation.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.news.media.api.ImageProvider
import com.news.news.api.NewsCategory
import com.news.news.presentation.*
import com.news.news.presentation.adapter.NewsRecyclerAdapter
import com.news.news.presentation.databinding.FragmentNewsBinding
import com.news.news.presentation.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment @Inject constructor(
    private val imageProvider: ImageProvider,
    private val newsViewModel: NewsViewModel
) : Fragment(), NewsItemCallback {
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private var newsRecyclerAdapter: NewsRecyclerAdapter? = null

    private val observer = Observer<NewsViewState> {
        when(it) {
            is NewsViewState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is NewsViewState.Success -> {
                binding.progressBar.visibility = View.GONE
                newsRecyclerAdapter?.setData(it.articles)
            }
            is NewsViewState.Error -> {
                binding.progressBar.visibility = View.GONE
                val serverError = getString(R.string.server_error, it)
                Snackbar.make(binding.filter, serverError, Snackbar.LENGTH_SHORT)

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        newsRecyclerAdapter =
            NewsRecyclerAdapter(
                imageProvider,
                this
            )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu, menu)
        val menuItem = menu.findItem(R.id.action_search)
        val searchView = SearchView(activity)
        menuItem.actionView = searchView
        loadHeadlinesBasedOnQuery(searchView)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentNewsBinding.inflate(inflater, container, false)
        newsViewModel.getHotNews(NewsCategory.Business)
        loadHeadlinesBasedOnFilter()
        newsViewModel.newsResponseLiveData.observe(viewLifecycleOwner, observer)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.newsRecycler.layoutManager = LinearLayoutManager(context)
        binding.newsRecycler.adapter = newsRecyclerAdapter
    }

    private fun loadHeadlinesBasedOnQuery(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { q ->
                    if (q.isNotEmpty()) {
                        queryHotNews(q)
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun queryHotNews(query: String) {
        val checkedChipIds = binding.chipGroup.checkedChipIds
        // should only be one chip, take 0th index
        val firstCheckedChip = checkedChipIds[0]
        getHotNews(firstCheckedChip, query)
    }

    private fun loadHeadlinesBasedOnFilter() {
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            getHotNews(checkedId)
        }
    }

    private fun getHotNews(checkedId: Int, query: String? = null) {
        when (checkedId) {
            R.id.business_chip -> {
                newsViewModel.getHotNews(category = NewsCategory.Business, query = query)
            }
            R.id.entertainment_chip -> {
                newsViewModel.getHotNews(category = NewsCategory.Entertainment, query = query)
            }
            R.id.general_chip -> {
                newsViewModel.getHotNews(category = NewsCategory.General, query = query)
            }
            R.id.health_chip -> {
                newsViewModel.getHotNews(category = NewsCategory.Health, query = query)
            }
            R.id.sports_chip -> {
                newsViewModel.getHotNews(category = NewsCategory.Sports, query = query)
            }
            R.id.science_chip -> {
                newsViewModel.getHotNews(category = NewsCategory.Science, query = query)
            }
            R.id.technology_chip -> {
                newsViewModel.getHotNews(category = NewsCategory.Technology, query = query)
            }
        }
    }

    override fun onNewsArticleClicked(articleUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(articleUrl))
        activity?.startActivity(intent)
    }
}