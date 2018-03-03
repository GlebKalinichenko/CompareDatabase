package com.example.wallet.comparedatabase

import android.app.Application
import com.example.wallet.comparedatabase.di.ApplicationComponent
import com.example.wallet.comparedatabase.di.ApplicationModule
import com.example.wallet.comparedatabase.di.DaggerApplicationComponent

/**
 * Created by glebkalinichenko on 03.03.18.
 */
class CompareDatabaseApplication : Application() {
    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this.applicationContext)).build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}