package com.example.wallet.comparedatabase.room

import android.arch.persistence.room.*
import io.reactivex.Flowable


/**
 * Created by glebkalinichenko on 03.03.18.
 */
@Dao
interface RoomUserDao {

    @Query("SELECT * FROM Users")
    fun selectAllUsers(): Flowable<MutableList<Users>>

    @Query("SELECT * FROM Users WHERE id = :userId LIMIT 1")
    fun selectUserById(userId: String): Flowable<Users>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(vararg users: Users): MutableList<Long>

    @Update
    fun updateUsers(vararg users: Users)

    @Delete
    fun deleteUsers(vararg users: Users)

/*    @Transaction
    fun insertAndDeleteInTransaction(newProduct: Product, oldProduct: Product) {
        // Anything inside this method runs in a single transaction.
        insert(newProduct)
        delete(oldProduct)
    }*/
}