package com.ashish.githubissueslist.ui.comments

import com.ashish.githubissueslist.model.CommentsModelClass
import db.CommentsClass
import util.Util

class CommentsPresenter(
    private val commentsList: ArrayList<CommentsModelClass>? = null
) {
    fun getCommentList(): List<CommentPresentation>? {
        return commentsList?.map { it.toCommentPresentation() }
    }
}
fun CommentsModelClass.toCommentPresentation(): CommentPresentation =
    CommentPresentation(
        avatarUrl = user.avatarUrl,
        commentId = id,
        commentTitle = "Title : $body",
        commentCreatedBy = "Created by: ${user.login}",
        commentCreatedDate = "Updated at:  ${Util.getFormattedDate(updatedAt)}",
        commentNumber = id.toString(),
    )

data class CommentPresentation(
    val avatarUrl: String,
    val commentId: Int,
    val commentTitle: String,
    val commentCreatedBy: String,
    val commentCreatedDate: String,
    val commentNumber: String
)