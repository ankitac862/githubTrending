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

    fun requestTrendingRepositories(forceRefresh: Boolean = false , page :Int) {
        viewModelScope.launch {
            trendingLiveData.value = Resource.Loading()
            val trendingRepositories = repository.getTrendingRepositories(forceRefresh , page)
            if (trendingRepositories.isNullOrEmpty()) {
                trendingLiveData.value = Resource.Error("List is null or empty")
            } else {
                trendingLiveData.value = Resource.Success(trendingRepositories)
            }

        }
    }


    fun searchLocal(userName: String){
        viewModelScope.launch {
            if(userName.isNotEmpty()) {
                if (!trendingLiveData.value?.data.isNullOrEmpty()){
                    val trendingRepositories = trendingLiveData.value?.data?.filter {
                        it.name.contains(
                            userName,
                            true
                        )
                    }
                    if (trendingRepositories.isNullOrEmpty()) {
                        trendingLiveData.value = Resource.Error("List is null or empty" ,trendingLiveData.value?.data)
                    } else {
                        trendingLiveData.value = Resource.Success(trendingRepositories)
                    }

                }
            }else{
                trendingLiveData.value = Resource.Loading()
                val trendingRepositories = repository.getTrendingRepositories(false , 0)
                if (trendingRepositories.isNullOrEmpty()) {
                    trendingLiveData.value = Resource.Error("List is null or empty" ,trendingLiveData.value?.data)
                } else {
                    trendingLiveData.value = Resource.Success(trendingRepositories)
                }
            }

        }
    }
}


