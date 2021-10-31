package com.ashish.githubissueslist.ui.issueslist

import com.ashish.githubissueslist.model.IssuesModel

sealed class IssuesListState {
    data class Loading(val isLoading: Boolean) : IssuesListState()
    data class Success(val entries: List<IssuesModel>) : IssuesListState()
    data class Error(val message: String) : IssuesListState()
}