package com.ashish.githubissueslist.ui.comments

import androidx.lifecycle.viewModelScope
import com.ashish.githubissueslist.base.BaseViewModel
import com.ashish.githubissueslist.ui.comments.CommentsState.*
import kotlinx.coroutines.launch
import rest.RepositoryImplementation

class CommentsListViewModel(
    private val repositoryImplementation: RepositoryImplementation
) : BaseViewModel<CommentsState>() {

    fun getComments(commentId: String) {
        uiState.value = Loading(true)
        viewModelScope.launch {
            try {
                uiState.value = Success(repositoryImplementation.getComments(commentId))
                uiState.value = Loading(false)
            } catch (exception: Exception) {
                uiState.value = Error("Error retrieving data")
                uiState.value = Loading(false)
            }
        }
    }
}