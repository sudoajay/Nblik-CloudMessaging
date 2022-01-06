package com.sudoajay.nblik.cloudmessaging

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.sudoajay.nblik.cloudmessaging.firebase.NotificationChannels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSystemDefaultOn()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannels.notificationOnCreate(applicationContext)
        }
    }

    private fun setSystemDefaultOn() {
        AppCompatDelegate.setDefaultNightMode(
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        )
    }

    fun isSystemDefaultOn(): Boolean {
        return resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }


}