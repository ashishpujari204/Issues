package com.ashish.githubissueslist.ui.comments

import db.CommentsClass
import util.Util

class IssuesPresenter(
    private val commentsList: ArrayList<CommentsClass>? = null
) {
    fun getCommentList(): List<CommentPresentation>? {
        return commentsList?.map { it.toCommentPresentation() }
    }
}

fun CommentsClass.toCommentPresentation(): CommentPresentation =
    CommentPresentation(
        avatarUrl = avatarUrl,
        commentId = id,
        commentTitle = "Title : $commentTitle",
        commentCreatedBy = "Created by: $commentCreatedBy",
        commentCreatedDate = "Updated at:  ${Util.getFormattedDate(commentCreatedDate)}",
        commentNumber = commentId.toString(),
    )

data class CommentPresentation(
    val avatarUrl: String,
    val commentId: Int,
    val commentTitle: String,
    val commentCreatedBy: String,
    val commentCreatedDate: String,
    val commentNumber: String
)