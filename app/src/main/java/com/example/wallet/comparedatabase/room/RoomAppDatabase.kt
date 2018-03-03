package com.example.wallet.comparedatabase.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.wallet.comparedatabase.dbentity.Users

/**
 * Created by glebkalinichenko on 03.03.18.
 */
@Database(entities = arrayOf(Users::class), version = 1)
abstract class RoomAppDatabase : RoomDatabase() {

    abstract fun userDao(): RoomUserDao
}