package com.maharani.maharaniapp.Utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {

    fun isNetworkConnected(activity: Activity): Boolean {
        val cm = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }
}