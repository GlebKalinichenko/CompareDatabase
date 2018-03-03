package com.example.wallet.comparedatabase.di

import com.example.wallet.comparedatabase.MainActivity
import dagger.Subcomponent

/**
 * Created by glebkalinichenko on 03.03.18.
 */
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        fun activityModule(module: ActivityModule): Builder
        fun build(): ActivityComponent
    }

    fun inject(activity: MainActivity)
}