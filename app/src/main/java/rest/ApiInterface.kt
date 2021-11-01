package rest

import com.ashish.githubissueslist.model.IssuesModel
import com.ashish.githubissueslist.model.CommentsModelClass
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("issues")
    suspend fun getIssuesList(): List<IssuesModel>

    @GET("issues/{comment_id}/comments")
    suspend fun getComments(
        @Path("comment_id") comment_id: String
    ): List<CommentsModelClass>
}