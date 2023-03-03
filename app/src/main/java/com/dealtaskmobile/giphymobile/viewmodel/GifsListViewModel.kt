package com.dealtaskmobile.giphymobile.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.dealtaskmobile.data.GifsModel
import com.dealtaskmobile.domain.usercase.FindGifQuery
import com.dealtaskmobile.domain.usercase.GetListGifs
import com.google.gson.Gson
import kotlinx.coroutines.*


class GifsListViewModel(
    private val getListGifs: GetListGifs,
    private val findGifQuery: FindGifQuery,

) : ViewModel() {


    private val listItemGifs = MutableLiveData<GifsModel>()


    fun getItemsGifs(): LiveData<GifsModel>{
        return listItemGifs
    }


    init {
        Log.w("ViewModel-Event","ViewModel created")
    }

    fun sendQueryGetGifs(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                setAsyncResult(getListGifs.execute())
            } catch (ex : java.lang.RuntimeException){
                Log.e("Error_Query", "sendQueryGetGifs: ${ex}")
            }
        }
    }


    fun getSearchResult(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                setAsyncResult(findGifQuery.execute(query = query))
            } catch (ex : java.lang.RuntimeException){
                Log.e("Error_Query", "sendQueryGetGifs: ${ex}")
            }
        }
    }

    private fun setAsyncResult(resultQuery: String){
        listItemGifs.postValue(Gson().fromJson(resultQuery,GifsModel::class.java))
    }


}