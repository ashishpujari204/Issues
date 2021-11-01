package com.ashish.githubissueslist.model


import com.google.gson.annotations.SerializedName

data class CommentsModelClass(
    @SerializedName("author_association")
    val authorAssociation: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user")
    val user: User?
)