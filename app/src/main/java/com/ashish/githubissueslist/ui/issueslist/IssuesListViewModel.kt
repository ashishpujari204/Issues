package com.ashish.githubissueslist.ui.issueslist

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ashish.githubissueslist.R
import com.ashish.githubissueslist.base.BaseViewModel
import kotlinx.coroutines.launch
import rest.RepositoryImplementation

class IssuesListViewModel(
    private val context : Context,
    private val repositoryImplementation: RepositoryImplementation
) : BaseViewModel<IssuesListState>() {

    fun getIssues() {
        uiState.value = IssuesListState.Loading(true)
        viewModelScope.launch {
            try {
                uiState.value = IssuesListState.Success(repositoryImplementation.getIssuesList())
                uiState.value = IssuesListState.Loading(false)
            } catch (exception: Exception) {
                uiState.value = IssuesListState.Error(context.getString(R.string.no_comments))
                uiState.value = IssuesListState.Loading(false)
            }
        }
    }
}