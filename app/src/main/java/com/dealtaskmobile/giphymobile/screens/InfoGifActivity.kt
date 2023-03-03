package com.dealtaskmobile.giphymobile.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.dealtaskmobile.data.DataGifsModel
import com.dealtaskmobile.giphymobile.R
import com.dealtaskmobile.giphymobile.databinding.ActivityInfoGifBinding
import kotlinx.android.synthetic.main.activity_info_gif.*

class InfoGifActivity : AppCompatActivity() {

    private var binding: ActivityInfoGifBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoGifBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_info_gif)
        init()
    }


    private fun setGlideImage(imageView: ImageView, urlString: String?){
        val circularProgressDrawable = CircularProgressDrawable(applicationContext)
        circularProgressDrawable.strokeWidth = 30f
        circularProgressDrawable.centerRadius = 60f
        circularProgressDrawable.start()

        Glide.with(applicationContext)
            .load(urlString)
            .placeholder(circularProgressDrawable)
            .centerCrop()
            .into(imageView)
    }


    private fun init(){
        val bundle: Bundle? = intent.extras
        val model: DataGifsModel? = intent.getParcelableExtra("info_about_gif")

        with(binding){
            if (model?.userModel?.urlAvatar == "IS_NULL"){
                tvUserViewInfo.visibility = View.GONE
                setGlideImage(tvImageCurrentGif,model.imagesGifsModel.originalGifsModel.urlImage)
                tvTitleGif.text = "Title: ${model.titleImage}"
            } else {
                setGlideImage(tvImageBackgroundProfile,model?.userModel!!.urlBanner)
                setGlideImage(tvImageAvatar, model.userModel?.urlAvatar)
                setGlideImage(tvImageCurrentGif,model.imagesGifsModel.originalGifsModel.urlImage)
                tvUserName.text = "User: ${model.userModel?.userName}"
                tvTitleGif.text = "Title: ${model.titleImage}"
            }
        }

    }


    fun BackToMainActivity(view:View){
        startActivity(Intent(this, MainActivity::class.java))
    }

}