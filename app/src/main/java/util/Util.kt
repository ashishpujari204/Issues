package util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import db.DatabaseBuilder
import db.DatabaseHelperImpl
import java.text.SimpleDateFormat
import java.util.*

class Util {
    companion object {
        private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        private const val OUTPUT_DATE_FORMAT = "MM-dd-yyyy"

        fun verifyAvailableNetwork(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val nw = connectivityManager.activeNetwork ?: return false
                val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
                return when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                    else -> false
                }
            } else {
                val nwInfo = connectivityManager.activeNetworkInfo ?: return false
                return nwInfo.isConnected
            }
        }

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

        fun getDBHelper(context: Context): DatabaseHelperImpl {
            return DatabaseHelperImpl(DatabaseBuilder.getInstance(context))
        }
    }
}