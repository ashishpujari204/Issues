package com.ashish.githubissueslist.ui.comments

import com.ashish.githubissueslist.model.CommentsModelClass

sealed class CommentsState {
    data class Loading(val isLoading: Boolean) : CommentsState()
    data class Success(val entries: List<CommentsModelClass>) : CommentsState()
    data class Error(val message: String) : CommentsState()
}