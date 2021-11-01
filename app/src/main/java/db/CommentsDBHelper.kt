package db

interface CommentsDBHelper {

    suspend fun insertAll(commentsClass: CommentsClass)

    suspend fun insertOrUpdate(commentsClass: CommentsClass)

    suspend fun deleteIssuesPresentation(commentsClass: CommentsClass)

    suspend fun getRowCount(): Int

    suspend fun getComments(): List<CommentsClass>
}