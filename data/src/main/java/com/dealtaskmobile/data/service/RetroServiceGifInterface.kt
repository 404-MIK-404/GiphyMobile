package com.dealtaskmobile.data.service

import com.dealtaskmobile.data.GifsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


private const val API_KEY = "gPjmgyC0OkCa4Uo8KPla2GCVtd6P8UXc"

interface RetroServiceGifInterface {


    @GET("trending?api_key=${API_KEY}")
    suspend fun getListGifs(@Query("limit") limit: Int = 50, @Query("rating") rating: String = "g"): GifsModel



    @GET("search?api_key=${API_KEY}")
    suspend fun getGifsResearch(@Query("q") query: String,@Query("limit") limit: Int = 75,@Query("offset") offset: Int = 0, @Query("rating") rating: String = "g", @Query("lang") lang: String = "en"): GifsModel

}