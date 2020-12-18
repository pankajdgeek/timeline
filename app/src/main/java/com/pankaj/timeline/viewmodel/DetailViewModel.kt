package com.pankaj.timeline.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pankaj.pankaj.util.printInfoLog
import com.pankaj.timeline.TimeLineApp
import com.pankaj.timeline.data.BaseResponse
import com.pankaj.timeline.data.Comment
import com.pankaj.timeline.ui.repository.DetailRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(private val repo: DetailRepository) : ViewModel() {
    var isLoading = ObservableBoolean()
    var title = ObservableField<String>()
    var body = ObservableField<String>()
    private val _saveUploadRequest = MutableLiveData<Boolean>()
    val saveUploadRequest = _saveUploadRequest
    var postId = -1
    var isBookMarkerd = ObservableBoolean()
    private val _commentList: MutableLiveData<List<Comment>> = MutableLiveData()
    val commentList: LiveData<List<Comment>> get() = _commentList
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage
    fun getComments(id: Int) {
        postId = id
        CoroutineScope(Dispatchers.IO).launch {
            isBookMarkerd.set(repo.isPostBookmared(id))
            repo.getComment(id).collect {
                isLoading.set(it == BaseResponse.Loading)
                when (it) {
                    BaseResponse.Loading -> {
                    }
                    is BaseResponse.Error -> {
                        _errorMessage.postValue(it.error)
                    }
                    is BaseResponse.Success<*> -> {
                        _commentList.postValue(it.data as List<Comment>)
                    }
                }
            }

        }
    }

    fun bookMarkPost() {
        CoroutineScope(Dispatchers.IO).launch {
//            if (TimeLineApp.isConnectNetwork) {
////                network call to upload fav list
//                repo.saveFavPostDB(postId, !isBookMarkerd.get(), true)
//            } else {
//                add worker to workmanager to upload it later when we have internet connected
                repo.saveFavPostDB(id= postId, isBookmarked =  !isBookMarkerd.get(), isUploaded = false)
                _saveUploadRequest.postValue(true)
//            }
            isBookMarkerd.set(!isBookMarkerd.get())
        }
    }

}