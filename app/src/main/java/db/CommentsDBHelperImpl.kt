package db

class CommentsDBHelperImpl(private val appDatabase: AppDatabase) : CommentsDBHelper {

    override suspend fun insertAll(commentsClass: CommentsClass) {
        appDatabase.commentsDAO().insertAll(commentsClass)
    }

    override suspend fun insertOrUpdate(commentsClass: CommentsClass) {
        appDatabase.commentsDAO().insertOrUpdate(commentsClass)
    }

    override suspend fun deleteIssuesPresentation(commentsClass: CommentsClass) {
        commentsClass.commentId.let { appDatabase.issueDAO().delete(it) }
    }

    override suspend fun getRowCount(): Int {
        return appDatabase.commentsDAO().getRowCount()
    }

    override suspend fun getComments(): List<CommentsClass> {
        return appDatabase.commentsDAO().getAllComments()
    }
}