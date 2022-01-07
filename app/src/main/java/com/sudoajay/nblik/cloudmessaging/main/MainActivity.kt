package com.sudoajay.nblik.cloudmessaging.main

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.sudoajay.nblik.cloudmessaging.BaseActivity
import com.sudoajay.nblik.cloudmessaging.R
import com.sudoajay.nblik.cloudmessaging.databinding.ActivityMainBinding
import com.sudoajay.nblik.cloudmessaging.helper.Toaster

class MainActivity : BaseActivity() {

    private var TAG = "MainActivityTAG"
    lateinit var binding: ActivityMainBinding
    private var isDarkTheme: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isDarkTheme = isSystemDefaultOn()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!isDarkTheme) {
                WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars =
                    true
            }

        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activity = this
        binding.lifecycleOwner = this

    }


    fun generateToken(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, getString(R.string.generateTokenFailed_Text), task.exception)
                Toaster.showToast( applicationContext,getString(R.string.generateTokenFailed_Text))

                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast

            Log.d(TAG,"Generated Token - $token")
            copyToClipboard(token)
            Toaster.showToast( applicationContext,getString(R.string.token_saved_text))
        })
    }
    private fun Context.copyToClipboard(text: CharSequence){
        val clipboard = ContextCompat.getSystemService(this, ClipboardManager::class.java)
        clipboard?.setPrimaryClip(ClipData.newPlainText("", text))

    }

}