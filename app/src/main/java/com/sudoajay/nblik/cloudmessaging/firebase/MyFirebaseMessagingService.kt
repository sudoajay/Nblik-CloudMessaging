package com.sudoajay.nblik.cloudmessaging.firebase

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.sudoajay.nblik.cloudmessaging.R
import com.sudoajay.nblik.cloudmessaging.main.MainActivity

class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val TAG = "MyFirebaseMsgService"


    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        Log.d(TAG, "From: ${remoteMessage.from}")


        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")

            if (true) {

                scheduleJob()
            } else {

                handleNow(remoteMessage)
            }
        }


        remoteMessage.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
        }


    }

    //    fun generateToken(){
//        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
//            if (!task.isSuccessful) {
//                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
//                return@OnCompleteListener
//            }
//
//            // Get new FCM registration token
//            val token = task.result
//
//            // Log and toast
//            val msg = getString(R.string.msg_token_fmt, token)
//            Log.d(TAG, msg)
//            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
//        })
//    }
    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")


        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String?) {
        // TODO: Implement this method to send token to your app server.
        Log.d(TAG, "sendRegistrationTokenToServer($token)")
    }


    private fun scheduleJob() {

//        val work = OneTimeWorkRequest.Builder(MyWorker::class.java).build()
//        WorkManager.getInstance(this).beginWith(work).enqueue()

    }

    private fun handleNow(remoteMessage: RemoteMessage) {
        Log.d(TAG, "Short lived task is done.")

        val data = remoteMessage.data
        val url = data["Url"]
        val notificationCompat: NotificationCompat.Builder =
            NotificationCompat.Builder(
                applicationContext,
                NotificationChannels.FireBase_PUSH_NOTIFICATION
            )
        notificationCompat.setSmallIcon(R.drawable.ic_notification_icon)

        notificationCompat.setContentIntent(createPendingIntent(url.toString()))

        FirebaseNotification(applicationContext).notifyCompat(
            remoteMessage.notification,
            notificationCompat
        )
    }


    @SuppressLint("UnspecifiedImmutableFlag")
    private fun createPendingIntent(link: String): PendingIntent? {
        return if (link.isNotBlank() || link.isNotEmpty()) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(link)
            PendingIntent.getActivity(
                this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        } else {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            PendingIntent.getActivity(
                this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT
            )
        }
    }



}