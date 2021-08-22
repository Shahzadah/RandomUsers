package com.checkfer.randomusers.data.extensions

import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity

fun ComponentActivity.launchDialpad(phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    startActivity(intent)
}

fun ComponentActivity.launchEmail(emailId: String) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf(emailId))
    }
    if (packageManager.resolveActivity(intent, 0) != null) {
        startActivity(intent)
    }
}