package com.pankaj.timeline.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pankaj.pankaj.util.printInfoLog
import com.pankaj.timeline.data.BaseResponse
import com.pankaj.timeline.data.Post
import com.pankaj.timeline.ui.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(private val repo: MainRepository) : ViewModel() {
    var isLoading = ObservableBoolean()
    var navigationSelected = 0
    val postClickListner = MutableLiveData<Post>()
    private val _postList: MutableLiveData<List<Post>> = MutableLiveData()
    val postList: LiveData<List<Post>> get() = _postList
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage
    var job: Job? = null
    fun getPost() {
        navigationSelected = 0
        if (job?.isActive ?: false) {
            job?.cancel()
        }
        job = CoroutineScope(Dispatchers.IO).launch {
            repo.getAllPost().collect {
                printInfoLog(this, "response--> $it")
                isLoading.set(it == BaseResponse.Loading)
                when (it) {
                    BaseResponse.Loading -> {
                        _postList.postValue(arrayListOf())
                    }
                    is BaseResponse.Error -> {
                        _errorMessage.postValue(it.error)
                    }
                    is BaseResponse.Success<*> -> {
                        launch {
                            repo.savePost(it.data as List<Post>)
                        }
                        _postList.postValue(it.data as List<Post>)
                    }
                }
            }
        }
    }

    fun getBookmarkedPost() {
        navigationSelected = 1
        if (job?.isActive ?: false) {
            job?.cancel()
        }
        job = CoroutineScope(Dispatchers.IO).launch {
            repo.getBookMarkedPost().collect {
                isLoading.set(it == BaseResponse.Loading)
                when (it) {
                    BaseResponse.Loading -> {
                        _postList.postValue(arrayListOf())
                    }
                    is BaseResponse.Error -> {
                        _errorMessage.postValue(it.error)
                    }
                    is BaseResponse.Success<*> -> {
                        launch {
                            repo.savePost(it.data as List<Post>)
                        }
                        _postList.postValue(it.data as List<Post>)
                    }
                }
            }
        }
    }

}