package db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class CommentsClass(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "commentId")
    var commentId: Int,
    @ColumnInfo(name = "commentCreatedDate")
    var commentCreatedDate: String,
    @ColumnInfo(name = "avatarUrl")
    var avatarUrl: String,
    @ColumnInfo(name = "commentTitle")
    var commentTitle: String,
    @ColumnInfo(name = "commentCreatedBy")
    var commentCreatedBy: String,
    @ColumnInfo(name = "commentNumber")
    var commentNumber: String
)
