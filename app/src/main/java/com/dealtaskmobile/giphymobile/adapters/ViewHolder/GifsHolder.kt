package com.dealtaskmobile.giphymobile.adapters.ViewHolder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.dealtaskmobile.giphymobile.R
import com.dealtaskmobile.giphymobile.adapters.GifsAdapter

class GifsHolder(item: View,listener: GifsAdapter.OnItemClickListener): RecyclerView.ViewHolder(item) {

    val tvImageGif = itemView.findViewById<ImageView>(R.id.tvImageGif)

    init {

        item.setOnClickListener{
            listener.onItemClick(position = adapterPosition)
        }

    }

}