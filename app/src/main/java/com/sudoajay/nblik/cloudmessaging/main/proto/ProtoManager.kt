package com.sudoajay.nblik.cloudmessaging.main.proto

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.sudoajay.nblik.cloudmessaging.R
import com.sudoajay.nblik.cloudmessaging.StatePreferences
import javax.inject.Inject

class ProtoManager @Inject constructor (var context: Context){
    val dataStoreStatePreferences : DataStore<StatePreferences> = context.stateDataStore


    suspend fun setFireBaseValue(darkMode: String) {
        dataStoreStatePreferences.updateData { preferences ->
            preferences.toBuilder()
                .setFireBaseValue(darkMode)
                .build()
        }
    }


    suspend fun setFireBaseDefaultValue(){
        dataStoreStatePreferences.updateData { preferences->
            preferences.toBuilder()
                .setFireBaseValue(context.getString(R.string.firBaseDefaultValue_text))
                .build()
        }
    }

    companion object {
        private const val DATA_STORE_FILE_NAME = "state_prefs.pb"

        private val Context.stateDataStore: DataStore<StatePreferences> by dataStore(
            fileName = DATA_STORE_FILE_NAME,
            serializer = StatePreferencesSerializer
        )

    }

}





