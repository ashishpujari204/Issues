package util

import android.content.Context
import db.CommentsDBHelperImpl
import db.DatabaseBuilder
import db.IssueDBHelperImpl
import java.text.SimpleDateFormat
import java.util.Date

class Util {
    companion object {
        private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        private const val OUTPUT_DATE_FORMAT = "MM-dd-yyyy"

        fun getFormattedDate(date: String): String {
            return try {
                val inputFormat = SimpleDateFormat(DATE_FORMAT)
                val outputFormat = SimpleDateFormat(OUTPUT_DATE_FORMAT)
                val date: Date = inputFormat.parse(date)
                outputFormat.format(date)
            } catch (e: Exception) {
                date
            }
        }

        fun getDBHelper(context: Context): IssueDBHelperImpl {
            return IssueDBHelperImpl(DatabaseBuilder.getInstance(context))
        }

        fun getCommentsDBHelper(context: Context): CommentsDBHelperImpl {
            return CommentsDBHelperImpl(DatabaseBuilder.getInstance(context))
        }
    }
}