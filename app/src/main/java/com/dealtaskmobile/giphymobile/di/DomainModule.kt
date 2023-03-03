package com.dealtaskmobile.giphymobile.di


import com.dealtaskmobile.domain.repository.GifsRepository
import com.dealtaskmobile.domain.usercase.FindGifQuery
import com.dealtaskmobile.domain.usercase.GetListGifs
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideFindGifQuery(gifsRepository: GifsRepository): FindGifQuery{
        return FindGifQuery(gifsRepository = gifsRepository)
    }

    @Provides
    fun provideGetListGifs(gifsRepository: GifsRepository): GetListGifs{
        return GetListGifs(gifsRepository = gifsRepository)
    }


}