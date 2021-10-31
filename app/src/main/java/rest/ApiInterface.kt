package rest

import com.ashish.githubissueslist.model.IssuesModel
import retrofit2.http.GET

interface ApiInterface {
    @GET("issues")
    suspend fun getIssuesList(): List<IssuesModel>
}