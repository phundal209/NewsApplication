package com.news.news.presentation

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.news.media.api.ImageProvider
import com.news.news.api.NewsArticle
import com.news.news.api.NewsCategory
import com.news.news.presentation.databinding.FragmentNewsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment @Inject constructor(
    private val imageProvider: ImageProvider,
    private val newsViewModel: NewsViewModel
) : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private var newsRecyclerAdapter: NewsRecyclerAdapter? = null

    private val observer = Observer<NewsObservableState> {
        when(it) {
            is NewsObservableState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is NewsObservableState.Success -> {
                binding.progressBar.visibility = View.GONE
                newsRecyclerAdapter?.setData(it.articles)
            }
            is NewsObservableState.Error -> {
                binding.progressBar.visibility = View.GONE
                val serverError = getString(R.string.server_error, it)
                Snackbar.make(binding.filter, serverError, Snackbar.LENGTH_SHORT)

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        newsRecyclerAdapter = NewsRecyclerAdapter(requireContext(), imageProvider)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu, menu)
        val menuItem = menu.findItem(R.id.action_search)
        val searchView = SearchView(activity)
        menuItem.actionView = searchView
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

    private fun loadHeadlinesBasedOnQuery() {
        //todo later
    }

    private fun loadHeadlinesBasedOnFilter() {
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.business_chip -> {
                    newsViewModel.getHotNews(category = NewsCategory.Business)
                }
                R.id.entertainment_chip -> {
                    newsViewModel.getHotNews(category = NewsCategory.Entertainment)
                }
                R.id.general_chip -> {
                    newsViewModel.getHotNews(category = NewsCategory.General)
                }
                R.id.health_chip -> {
                    newsViewModel.getHotNews(category = NewsCategory.Health)
                }
                R.id.sports_chip -> {
                    newsViewModel.getHotNews(category = NewsCategory.Sports)
                }
                R.id.science_chip -> {
                    newsViewModel.getHotNews(category = NewsCategory.Science)
                }
                R.id.technology_chip -> {
                    newsViewModel.getHotNews(category = NewsCategory.Technology)
                }
            }
        }
    }
}