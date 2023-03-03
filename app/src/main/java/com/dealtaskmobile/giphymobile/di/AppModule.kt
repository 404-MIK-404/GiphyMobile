package com.dealtaskmobile.giphymobile.di

import android.content.Context
import com.dealtaskmobile.data.service.RetroServiceGifInterface
import com.dealtaskmobile.domain.usercase.FindGifQuery
import com.dealtaskmobile.domain.usercase.GetListGifs
import com.dealtaskmobile.giphymobile.viewmodel.GifsListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule(val context: Context) {

    @Provides
    fun provideContext(): Context{
        return context
    }

    @Provides
    fun provideGifsListViewModelFactory(
        getListGifs: GetListGifs,
        findGifQuery: FindGifQuery,
        ): GifsListViewModelFactory {

        return GifsListViewModelFactory(
            getListGifs = getListGifs,
            findGifQuery = findGifQuery)
    }


}