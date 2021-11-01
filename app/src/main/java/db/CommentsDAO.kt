package db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CommentsDAO {

    @Query("SELECT * FROM comments where commentId = :commentId")
    suspend fun checkIssueID(commentId: Int): List<CommentsClass>

    @Insert
    suspend fun insertAll(commentsClass: CommentsClass)

    @Query("DELETE FROM comments where commentId = :commentId")
    suspend fun delete(commentId: Int): Int

    suspend fun insertOrUpdate(commentsClass: CommentsClass) {
        if (checkIssueID(commentsClass.commentId).isEmpty()) {
            insertAll(commentsClass)
        }
    }

    @Query("SELECT count(*) from comments where commentId = :commentId")
    fun getRowCount(commentId : String): Int

    @Query("SELECT * FROM comments where commentId = :commentId")
    suspend fun getAllComments(commentId: String): List<CommentsClass>
}