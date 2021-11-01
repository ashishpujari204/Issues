package db

interface CommentsDBHelper {

    suspend fun insertAll(commentsClass: CommentsClass)

    suspend fun insertOrUpdate(commentsClass: CommentsClass)

    suspend fun deleteIssuesPresentation(commentsClass: CommentsClass)

    suspend fun getRowCount(commentId : String): Int

    suspend fun getComments(commentId: String): List<CommentsClass>
}