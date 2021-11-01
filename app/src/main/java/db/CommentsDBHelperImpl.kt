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

    override suspend fun getRowCount(commentsId : String): Int {
        return appDatabase.commentsDAO().getRowCount(commentsId)
    }

    override suspend fun getComments(commentsId : String): List<CommentsClass> {
        return appDatabase.commentsDAO().getAllComments(commentsId)
    }
}