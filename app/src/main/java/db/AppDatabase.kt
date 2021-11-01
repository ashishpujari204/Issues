package db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [IssuesClass::class,CommentsClass::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun issueDAO(): IssuesDAO
    abstract fun commentsDAO(): CommentsDAO
}