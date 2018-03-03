package com.example.wallet.comparedatabase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.wallet.comparedatabase.dbentity.Users
import com.example.wallet.comparedatabase.di.ActivityModule
import com.example.wallet.comparedatabase.room.RoomAppDatabase
import com.example.wallet.comparedatabase.room.RoomUserDao
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var roomAppDatabase: RoomAppDatabase
    lateinit var roomUserDao: RoomUserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectComponents()
    }

    private fun injectComponents() {
        (application as CompareDatabaseApplication).appComponent.activityComponent().activityModule(ActivityModule())
                .build().inject(this)
        roomUserDao = roomAppDatabase.userDao()
    }

    override fun onStart() {
        super.onStart()
        setupListener()
    }

    private fun setupListener() {
        setupRoomListener()

    }

    private fun setupRoomListener() {
        button_select_room.setOnClickListener {
            roomUserDao.selectAllUsers().subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe {
                Log.d("", "")
            }
        }

        button_insert_room.setOnClickListener {
            var user1 = Users(0,"gleb", "kalinichenko", 24)
            var user2 = Users(0,"gleb", "kalinichenko", 24)

            Single.fromCallable { roomUserDao.insertUsers(user1, user2) }.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe {
                i -> Log.d("", "")
            }
        }
    }
}
