package db

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override suspend fun insertAll(issuesClass: IssuesClass) {
        appDatabase.issueDAO().insertAll(issuesClass)
    }

    override suspend fun insertOrUpdate(issuesClass: IssuesClass) {
        appDatabase.issueDAO().insertOrUpdate(issuesClass)
    }

    override suspend fun deleteIssuesPresentation(issuesClass: IssuesClass) {
        issuesClass.issueId.let { appDatabase.issueDAO().delete(it) }
    }

    override suspend fun getRowCount(): Int {
        return appDatabase.issueDAO().getRowCount()
    }

    override suspend fun getIssues(): List<IssuesClass> {
        return appDatabase.issueDAO().getAllIssue()
    }
}