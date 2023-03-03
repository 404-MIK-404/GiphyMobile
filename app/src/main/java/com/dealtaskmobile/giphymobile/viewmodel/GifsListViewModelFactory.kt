package com.dealtaskmobile.giphymobile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dealtaskmobile.data.service.RetroServiceGifInterface
import com.dealtaskmobile.domain.usercase.FindGifQuery
import com.dealtaskmobile.domain.usercase.GetListGifs

class GifsListViewModelFactory(
    private val getListGifs: GetListGifs,
    private val findGifQuery: FindGifQuery,
    ): ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GifsListViewModel(
            getListGifs = getListGifs,
            findGifQuery = findGifQuery,
        ) as T
    }
}