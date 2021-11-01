package rest

open class RepositoryImplementation(
    private var apiInterface: ApiInterface
) {
    suspend fun getIssuesList() = apiInterface.getIssuesList()
    suspend fun getComments(commentId : String) = apiInterface.getComments(commentId)
}