package com.example.wallet.comparedatabase.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by glebkalinichenko on 03.03.18.
 */
@Entity(tableName = "Users")
class Users(@PrimaryKey(autoGenerate = true) var id: Long = 0L, @ColumnInfo(name = "first_name") var firstName: String,
            @ColumnInfo(name = "last_name") var lastName: String, var age: Int) {
}