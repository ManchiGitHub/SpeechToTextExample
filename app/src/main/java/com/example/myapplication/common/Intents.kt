package com.example.myapplication.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

object AppIntents {
    fun openDetailsSettings(context: Context){
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        with(intent) {
            data = Uri.fromParts("package", context.packageName, null)
            addCategory(Intent.CATEGORY_DEFAULT)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        }
        context.startActivity(intent)
    }
}