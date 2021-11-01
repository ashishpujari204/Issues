package com.ashish.githubissueslist.ui.comments

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.ashish.githubissueslist.R
import com.ashish.githubissueslist.base.BaseViewModel
import com.ashish.githubissueslist.ui.comments.CommentsState.Loading
import com.ashish.githubissueslist.ui.comments.CommentsState.Success
import com.ashish.githubissueslist.ui.comments.CommentsState.Error
import kotlinx.coroutines.launch
import rest.RepositoryImplementation

class CommentsListViewModel(
    private val context : Context,
    private val repositoryImplementation: RepositoryImplementation
) : BaseViewModel<CommentsState>() {

    fun getComments(commentId: String) {
        uiState.value = Loading(true)
        viewModelScope.launch {
            try {
                uiState.value = Success(repositoryImplementation.getComments(commentId))
                uiState.value = Loading(false)
            } catch (exception: Exception) {
                uiState.value = Error(context.getString(R.string.no_comments))
                uiState.value = Loading(false)
            }
        }
    }
}