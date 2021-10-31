package com.ashish.githubissueslist.ui.issueslist

import com.ashish.githubissueslist.model.IssuesModel
import util.Util

class IssuesPresenter(
    private val issueList: ArrayList<IssuesModel>? = null
) {
    fun getIssueList(): List<IssuesPresentation>? {
        return issueList?.map { it.toIssuePresentation() }
    }
}

fun IssuesModel.toIssuePresentation(): IssuesPresentation =
    IssuesPresentation(
        avatarUrl =user.avatarUrl,
        issueId = id,
        issueTitle = "Title : $title",
        issueDescription = "Description : ${body?.replace("(?m)^[ \t]*\r?\n", "")}",
        issueCreatedBy = "Created by: ${user.login}",
        issueCreatedDate = "Updated at:  ${Util.getFormattedDate(updatedAt)}"
    )

data class IssuesPresentation(
    val avatarUrl: String,
    val issueId: Int,
    val issueTitle: String,
    val issueDescription: String?,
    val issueCreatedBy: String,
    val issueCreatedDate: String
)