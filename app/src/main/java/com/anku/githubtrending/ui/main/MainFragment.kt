package com.anku.githubtrending.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.anku.githubtrending.R
import com.anku.githubtrending.databinding.FragmentMainBinding
import com.anku.githubtrending.model.Repo
import com.anku.githubtrending.model.Resource
import com.anku.githubtrending.ui.main.adapter.ReposAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class MainFragment : Fragment() {
    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: FragmentMainBinding
    private val adapter by lazy { ReposAdapter(requireContext()) }
    private val viewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        initTrendingView()
        handlePullToRefreshAction()
        handleRetryButtonAction()
        observeLiveData()
        fetchRepos()
    }

    private fun initTrendingView() {
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.setHasFixedSize(true)

        binding.recycler.adapter = adapter

        binding.recycler.addItemDecoration(
            DividerItemDecoration(
                binding.recycler.context,
                (binding.recycler.layoutManager as LinearLayoutManager).orientation
            )
        )
    }

    private fun handlePullToRefreshAction() {
        binding.swipeRefresh.setOnRefreshListener {
            showShimmerLoading()
            fetchRepos(true)
        }
    }

    private fun handleRetryButtonAction() {
        binding.errorLayout.retry.setOnClickListener {
            fetchRepos()
        }
    }

    private fun fetchRepos(forceRefresh: Boolean = false) {
        viewModel
            .requestTrendingRepositories(forceRefresh)
    }

    private fun observeLiveData() {
        viewModel.trendingLiveData
            .observe(viewLifecycleOwner, Observer {
                when (it) {
                    is Resource.Success -> {
                        hideShimmerLoading()
                        binding.swipeRefresh.visibility = View.VISIBLE
                        binding.errorLayout.layout.visibility = View.GONE
                        binding.recycler.visibility = View.VISIBLE
                        adapter.repositories = it.data as ArrayList<Repo>
                        adapter.notifyDataSetChanged()
                    }
                    is Resource.Loading -> {

                        binding.swipeRefresh.visibility  = View.GONE
                        binding.recycler.visibility = View.VISIBLE
                    }
                    is Resource.Error -> {
                        hideShimmerLoading()
                        binding.recycler.visibility = View.GONE
                        binding.swipeRefresh.visibility = View.GONE
                        binding.errorLayout.layout.visibility = View.VISIBLE


                    }
                }

            })
    }
    private fun hideShimmerLoading() {
        binding.shimmerLayout.shimmerLayout.hideShimmer()
        binding.shimmerLayout.shimmerLayout.visibility = View.GONE
        binding.swipeRefresh.isRefreshing = false
    }

    private fun showShimmerLoading() {
        binding.shimmerLayout.shimmerLayout.showShimmer(true)
        binding.shimmerLayout.shimmerLayout.visibility = View.VISIBLE
        binding.errorLayout.layout.visibility = View.GONE
    }


}