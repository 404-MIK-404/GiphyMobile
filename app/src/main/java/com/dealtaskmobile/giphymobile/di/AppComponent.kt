package com.dealtaskmobile.giphymobile.di

import com.dealtaskmobile.giphymobile.screens.MainActivity
import dagger.Component

@Component(modules = [AppModule::class,DataModule::class,DomainModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

}