package com.example.wallet.comparedatabase.di

import android.arch.persistence.room.Room
import android.content.Context
import com.example.wallet.comparedatabase.room.RoomAppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by glebkalinichenko on 03.03.18.
 */
@Module
class ApplicationModule(var context: Context) {
    @Provides @Singleton
    fun provideContext() = context

    @Provides @Singleton
    fun provideRoomDatabase(): RoomAppDatabase = Room.databaseBuilder(context, RoomAppDatabase::class.java, "database-name").build()
}