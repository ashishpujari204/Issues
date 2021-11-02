package com.ashish.githubissueslist

import com.ashish.githubissueslist.ui.comments.CommentsListViewModel
import com.ashish.githubissueslist.ui.comments.CommentsState
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import rest.RepositoryImplementation

class CommentTest : KoinTest {

    @Mock
    private lateinit var commentsListViewModel: CommentsListViewModel

    private val repositoryImplementation: RepositoryImplementation by inject()

    companion object {
        const val DEFAULT_DATE = "2021-10-29T18:25:32Z"
        const val RESULT_DATE = "10-29-2021"
    }

    @Test
    fun testUpdatedDate() {
        val formattedDate = util.Util.getFormattedDate(
            DEFAULT_DATE
        )
        Assert.assertEquals(RESULT_DATE, formattedDate)
    }

    @Test
    fun testCommentListAPI() = runBlocking {
        commentsListViewModel = CommentsListViewModel(repositoryImplementation)
        commentsListViewModel.uiState().observeForever {
            when (it) {
                is CommentsState.Success -> {
                    assert(true)
                }
                else -> assert(false)
            }
        }
    }
}