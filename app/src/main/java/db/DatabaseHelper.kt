package db

interface DatabaseHelper {

    suspend fun insertAll(issuesClass: IssuesClass)

    suspend fun insertOrUpdate(issuesClass: IssuesClass)

    suspend fun deleteIssuesPresentation(issuesClass: IssuesClass)

    suspend fun getRowCount(): Int

    suspend fun getIssues(): List<IssuesClass>
}