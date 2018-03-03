package com.example.wallet.comparedatabase

import android.app.Application
import com.example.wallet.comparedatabase.di.ApplicationComponent
import com.example.wallet.comparedatabase.di.ApplicationModule
import com.example.wallet.comparedatabase.di.DaggerApplicationComponent
import com.example.wallet.comparedatabase.objectbox.MyObjectBox
import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser

/**
 * Created by glebkalinichenko on 03.03.18.
 */
class CompareDatabaseApplication : Application() {
    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this.applicationContext)).build()
    }

    lateinit var boxStore: BoxStore

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)

        boxStore = MyObjectBox.builder().androidContext(this.applicationContext).build()
        if (BuildConfig.DEBUG) {
            AndroidObjectBrowser(boxStore).start(this);
        }
    }
}