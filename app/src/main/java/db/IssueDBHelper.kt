package db

interface IssueDBHelper {

    suspend fun insertAll(issuesClass: IssuesClass)

    suspend fun insertOrUpdate(issuesClass: IssuesClass)

    suspend fun deleteIssuesPresentation(issuesClass: IssuesClass)

    suspend fun getRowCount(): Int

    suspend fun getIssues(): List<IssuesClass>
}