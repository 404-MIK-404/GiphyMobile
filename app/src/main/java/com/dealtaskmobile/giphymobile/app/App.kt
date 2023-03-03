package com.dealtaskmobile.giphymobile.app

import android.app.Application
import com.dealtaskmobile.giphymobile.di.AppComponent
import com.dealtaskmobile.giphymobile.di.AppModule
import com.dealtaskmobile.giphymobile.di.DaggerAppComponent

class App : Application() {

    var appComponent: AppComponent? = null


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
                        .builder()
                        .appModule(AppModule(context = this))
                        .build()
    }
}