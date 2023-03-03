package com.dealtaskmobile.giphymobile.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop
import com.dealtaskmobile.data.DataGifsModel
import com.dealtaskmobile.data.GifsModel
import com.dealtaskmobile.giphymobile.R
import com.dealtaskmobile.giphymobile.adapters.ViewHolder.GifsHolder

class GifsAdapter(val context: Context):  RecyclerView.Adapter<GifsHolder>() {


    val gifsList = ArrayList<DataGifsModel>()


    val gifsAllItems = ArrayList<DataGifsModel>()

    lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {

        fun onItemClick(position: Int)

    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_gifs,parent,false)
        return GifsHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: GifsHolder, position: Int) {
        val data = gifsList[position]

        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 30f
        circularProgressDrawable.centerRadius = 60f
        circularProgressDrawable.start()

        Glide.with(context)
            .asGif()
            .load(data.imagesGifsModel.originalGifsModel.urlImage)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .placeholder(circularProgressDrawable)
            .centerCrop()
            .into(holder.tvImageGif)

    }

    override fun getItemCount(): Int {
        return gifsList.size
    }


    @SuppressLint("NotifyDataSetChanged")
    fun addAllGifs(gifsItemsList: GifsModel){
        gifsAllItems.addAll(gifsItemsList.dataGifsList)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItemGifs(page: Int){
        for (i in (page * 25)until ((page + 1) * 25)){
            if (i >= gifsAllItems.size){
                break
            } else {
                gifsList.add(gifsAllItems.get(i))
            }
        }
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearData(){
        gifsList.clear()
        gifsAllItems.clear()
        notifyDataSetChanged()
    }

    fun getItemFromPosition(position: Int): DataGifsModel {
        return gifsList.get(position)
    }

}