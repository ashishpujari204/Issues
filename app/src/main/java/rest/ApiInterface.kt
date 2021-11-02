package rest

import com.ashish.githubissueslist.model.CommentsModelClass
import com.ashish.githubissueslist.model.IssuesModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("issues")
    suspend fun getIssuesList(): List<IssuesModel>

    @GET("issues/{commentId}/comments")
    suspend fun getComments(@Path("commentId") commentId: String): List<CommentsModelClass>
}