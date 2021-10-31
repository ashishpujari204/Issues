package com.ashish.githubissueslist.model


import com.google.gson.annotations.SerializedName

data class IssuesModel(
    @SerializedName("body")
    val body: String?,
    @SerializedName("closed_at")
    val closedAt: Any,
    @SerializedName("comments")
    val comments: Int,
    @SerializedName("number")
    val number: Int,
    @SerializedName("comments_url")
    val commentsUrl: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("events_url")
    val eventsUrl: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("labels")
    val labels: List<Any>,
    @SerializedName("labels_url")
    val labelsUrl: String,
    @SerializedName("repository_url")
    val repositoryUrl: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("user")
    val user: User
)