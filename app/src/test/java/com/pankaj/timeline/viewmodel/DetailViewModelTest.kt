package com.pankaj.timeline.viewmodel

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.pankaj.timeline.TimeLineApp
import com.pankaj.timeline.data.BaseResponse
import com.pankaj.timeline.data.Comment
import com.pankaj.timeline.db.AppDatabase
import com.pankaj.timeline.network.Api
import com.pankaj.timeline.ui.repository.DetailRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private val db: AppDatabase = mock()
    private val api: Api = mock()
    private val detailRepo = DetailRepository(db = db, api = api)

    @Before
    fun setup() {
        TimeLineApp.isConnectNetwork = true
    }

    @Test
    fun whenGetPostComments_thenReturnSuccess() = runBlocking {
        mockCommentResponse()
        launch {
            detailRepo.getComment(1).collect { response ->
                when (response) {
                    BaseResponse.Loading -> {
                    }
                    is BaseResponse.Error -> {
                    }
                    is BaseResponse.Success<*> -> {
                        assertEquals(TEST_COMMENTS, response.data)
                    }
                }
            }

        }.join()
    }


    private suspend fun mockCommentResponse() {
        whenever(api.getPostComments(1)) doReturn (TEST_COMMENTS)
    }


    companion object {
        private val TEST_COMMENTS = listOf(
            Comment(
                postId = 1,
                id = 1,
                name = "id labore ex et quam laborum",
                email = "Eliseo@gardner.biz",
                body = "laudantium enim quasi est quidem magnam voluptate ipsam eos\ntempora quo necessitatibus\ndolor quam autem quasi\nreiciendis et nam sapiente accusantium"
            )
        )

        private val TEST_NETWORK_ERROR = BaseResponse.Error("Check your internet")
    }
}