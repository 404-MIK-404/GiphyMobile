package com.dealtaskmobile.data.repository

import com.dealtaskmobile.data.service.RetroServiceGifInterface
import com.dealtaskmobile.domain.repository.GifsRepository
import com.google.gson.Gson

class GifsRepositoryImpl(val retroServiceGifInterface: RetroServiceGifInterface
                        ) : GifsRepository {

    override suspend fun sendQueryGetListGifs() : String {
        return Gson().toJson(retroServiceGifInterface.getListGifs())
    }

    override suspend fun findNameGifQuery(query: String): String {
        return Gson().toJson(retroServiceGifInterface.getGifsResearch(query=query))
    }

}