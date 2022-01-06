package com.sudoajay.nblik.cloudmessaging.firebase

import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.sudoajay.nblik.cloudmessaging.R


object NotificationChannels {
    private const val GROUP_SERVICE = "com.sudoajay.nblik.cloudmessaging.firebase_service"
    const val FireBase_PUSH_NOTIFICATION = "com.sudoajay.nblik.cloudmessaging.firebase_push_notification"



    @RequiresApi(Build.VERSION_CODES.O)
    fun notificationOnCreate(context: Context) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannelGroup(
            NotificationChannelGroup(
                GROUP_SERVICE,
                context.getString(R.string.notifications_group_service)
            )
        )

        val firebaseChannel = NotificationChannel(
            FireBase_PUSH_NOTIFICATION,
            context.getString(R.string.firebase_channel_id),
            NotificationManager.IMPORTANCE_DEFAULT
        )
        firebaseChannel.description = context.getString(R.string.firebase_channel_id)
        firebaseChannel.group = GROUP_SERVICE
        firebaseChannel.setShowBadge(false)
        notificationManager.createNotificationChannel(firebaseChannel)


    }
}