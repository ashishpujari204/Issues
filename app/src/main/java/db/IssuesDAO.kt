package db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IssuesDAO {

    @Query("SELECT * FROM issues where issueId = :issueId")
    suspend fun checkIssueID(issueId: Int): List<IssuesClass>

    @Insert
    suspend fun insertAll(issuesClass: IssuesClass)

    @Query("DELETE FROM issues where issueId = :issueId")
    suspend fun delete(issueId: Int): Int

    suspend fun insertOrUpdate(issuesClass: IssuesClass) {
        if (checkIssueID(issuesClass.issueId).isEmpty()) {
            insertAll(issuesClass)
        }
    }

    @Query("SELECT count(*) from issues")
    fun getRowCount(): Int

    @Query("SELECT * FROM issues")
    suspend fun getAllIssue(): List<IssuesClass>
}