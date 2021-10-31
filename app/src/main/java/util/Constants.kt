package util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class Constants {
    companion object {
        const val DB_NAME = "issues_list"
        const val BASE_URL = "https://api.github.com/repos/square/okhttp/"
        /**
         * Application minSdkVersion is 23
         */
        fun isInternetAvailable(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }
    }
}