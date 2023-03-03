package com.dealtaskmobile.domain.repository

interface GifsRepository {


    suspend fun sendQueryGetListGifs() : String

    suspend fun findNameGifQuery(query: String): String


}