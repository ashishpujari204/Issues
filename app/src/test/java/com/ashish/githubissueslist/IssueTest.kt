package com.ashish.githubissueslist

import com.ashish.githubissueslist.ui.issueslist.IssuesListState
import com.ashish.githubissueslist.ui.issueslist.IssuesListViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import rest.RepositoryImplementation

class IssueTest : KoinTest {

    @Mock
    private lateinit var issueListViewModel: IssuesListViewModel

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
    fun testIssueListAPI() = runBlocking {
        issueListViewModel = IssuesListViewModel(repositoryImplementation)
        issueListViewModel.uiState().observeForever {
            when (it) {
                is IssuesListState.Success -> {
                    assert(true)
                }
                else -> assert(false)
            }
        }
    }
}