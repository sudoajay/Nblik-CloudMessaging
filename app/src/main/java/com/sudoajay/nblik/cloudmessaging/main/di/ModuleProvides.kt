package com.sudoajay.nblik.cloudmessaging.main.di

import android.content.Context
import com.sudoajay.nblik.cloudmessaging.firebase.FirebaseNotification
import com.sudoajay.nblik.cloudmessaging.main.proto.ProtoManager

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleProvides {

    @Singleton
    @Provides
    fun providesProtoManger( @ApplicationContext appContext: Context): ProtoManager = ProtoManager(appContext)

    @Singleton
    @Provides
    fun providesFirebaseNotification( @ApplicationContext appContext: Context): FirebaseNotification = FirebaseNotification(appContext)


}

