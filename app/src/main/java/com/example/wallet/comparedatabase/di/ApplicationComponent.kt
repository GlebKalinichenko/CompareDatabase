package com.example.wallet.comparedatabase.di

import com.example.wallet.comparedatabase.CompareDatabaseApplication
import dagger.Component
import javax.inject.Singleton

/**
 * Created by glebkalinichenko on 03.03.18.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun activityComponent(): ActivityComponent.Builder
    fun inject(application: CompareDatabaseApplication)
}