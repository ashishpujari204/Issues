package db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "issues")
data class IssuesClass(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "issueId")
    var issueId: Int,
    @ColumnInfo(name = "issueCreatedDate")
    var issueCreatedDate: String,
    @ColumnInfo(name = "avatarUrl")
    var avatarUrl: String,
    @ColumnInfo(name = "issueTitle")
    var issueTitle: String,
    @ColumnInfo(name = "issueDescription")
    var issueDescription: String,
    @ColumnInfo(name = "issueCreatedBy")
    var issueCreatedBy: String
)
