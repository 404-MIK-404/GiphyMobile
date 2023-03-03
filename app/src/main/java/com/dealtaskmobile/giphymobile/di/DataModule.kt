package com.dealtaskmobile.giphymobile.di

import com.dealtaskmobile.data.repository.GifsRepositoryImpl
import com.dealtaskmobile.data.instance.RetroInstance
import com.dealtaskmobile.data.service.RetroServiceGifInterface
import com.dealtaskmobile.domain.repository.GifsRepository
import dagger.Module
import dagger.Provides



@Module
class DataModule {

    @Provides
    fun provideGifsRepository(retroServiceGifInterface: RetroServiceGifInterface): GifsRepository {
        return GifsRepositoryImpl(retroServiceGifInterface = retroServiceGifInterface)
    }

    @Provides
    fun provideRetroService() : RetroServiceGifInterface {
        return RetroInstance.getRetroInstance().create(RetroServiceGifInterface::class.java)
    }


}