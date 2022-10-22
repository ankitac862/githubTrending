package com.anku.githubtrending.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anku.githubtrending.ITrendingRepository
import com.anku.githubtrending.model.Repo
import com.anku.githubtrending.model.Resource
import com.anku.githubtrending.repo.network.TrendingRepositoryImpl
import kotlinx.coroutines.launch

// ViewModel for fetching data from repository or database.
class MainViewModel( private val repository: ITrendingRepository)
    : ViewModel() {
    val trendingLiveData: MutableLiveData<Resource<List<Repo>>> = MutableLiveData()

    fun requestTrendingRepositories(forceRefresh: Boolean = false) {
        viewModelScope.launch {
            trendingLiveData.value = Resource.Loading()
            val trendingRepositories = repository.getTrendingRepositories(forceRefresh)
            if (trendingRepositories.isNullOrEmpty()) {
                trendingLiveData.value = Resource.Error("List is null or empty")
            } else {
                trendingLiveData.value = Resource.Success(trendingRepositories)
            }

        }
    }
}


