package com.dealtaskmobile.domain.usercase

import com.dealtaskmobile.domain.repository.GifsRepository

class GetListGifs(private val gifsRepository: GifsRepository) {


    suspend fun execute() : String{
        return gifsRepository.sendQueryGetListGifs()
    }


}