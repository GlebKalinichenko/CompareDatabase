package com.example.wallet.comparedatabase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.wallet.comparedatabase.room.Users
import com.example.wallet.comparedatabase.di.ActivityModule
import com.example.wallet.comparedatabase.objectbox.Person
import com.example.wallet.comparedatabase.room.RoomAppDatabase
import com.example.wallet.comparedatabase.room.RoomUserDao
import io.objectbox.Box
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import io.objectbox.reactive.DataObserver
import io.objectbox.android.AndroidScheduler
import io.objectbox.reactive.DataSubscriptionList

class MainActivity : AppCompatActivity() {
    private val subscriptions = DataSubscriptionList()

    // Room
    @Inject
    lateinit var roomAppDatabase: RoomAppDatabase
    lateinit var roomUserDao: RoomUserDao

    // ObjectBox
    lateinit var personBox: Box<Person>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectComponents()
    }

    private fun injectComponents() {
        (application as CompareDatabaseApplication).appComponent.activityComponent().activityModule(ActivityModule())
                .build().inject(this)

        // Room
        roomUserDao = roomAppDatabase.userDao()
        // ObjectBox
        personBox = (application as CompareDatabaseApplication).boxStore.boxFor(Person::class.java)
    }

    override fun onStart() {
        super.onStart()
        setupListener()
    }

    private fun setupListener() {
        setupRoomListener()
        setupObjectBox()
    }

    private fun setupRoomListener() {
        button_select_room.setOnClickListener {
            roomUserDao.selectAllUsers().subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe {
                Log.d("", "")
            }
        }

        button_insert_room.setOnClickListener {
            var user1 = Users(0, "gleb", "kalinichenko", 24)
            var user2 = Users(0, "gleb", "kalinichenko", 24)

            Single.fromCallable { roomUserDao.insertUsers(user1, user2) }.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe {
                i -> Log.d("", "")
            }
        }
    }

    private fun setupObjectBox() {
        button_objectbox_select.setOnClickListener {
            var selectAllQuery = personBox.query().build()
            selectAllQuery.subscribe(subscriptions).on(AndroidScheduler.mainThread())
                    .observer(DataObserver<MutableList<Person>> {
                        persons -> Log.d("", "")
                        subscriptions.cancel()
                    });
        }

        button_objectbox_insert.setOnClickListener {
            var person = Person(0, "gleb", "kalinichenko", 24)
            personBox.put(person)
        }

        button_objectbox_update.setOnClickListener {
            var person = personBox.get(1)
            person.firstName = "Change"
            personBox.put(person)
        }
    }

    override fun onDestroy() {
        subscriptions.cancel()
        super.onDestroy()
    }
}
