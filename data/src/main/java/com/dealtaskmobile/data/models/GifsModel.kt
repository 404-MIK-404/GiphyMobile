package com.dealtaskmobile.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GifsModel(
    @SerializedName("data")
    val dataGifsList: List<DataGifsModel>
) : Parcelable

@Parcelize
data class DataGifsModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val imagesGifsModel: ImageGifsModel,
    @SerializedName("title")
    val titleImage: String,
    @SerializedName("import_datetime")
    val dateCreate: String,
    @SerializedName("user")
    var userModel: UserModel?
) : Parcelable

@Parcelize
data class ImageGifsModel(
    @SerializedName("original")
    val originalGifsModel:OriginalGifsModel
) : Parcelable

@Parcelize
data class OriginalGifsModel(

    @SerializedName("url")
    val urlImage: String,
) : Parcelable

@Parcelize
data class UserModel(
    @SerializedName("avatar_url")
    val urlAvatar: String?,
    @SerializedName("banner_url")
    val urlBanner: String,
    @SerializedName("username")
    val userName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("is_verified")
    val isVerified: Boolean
) : Parcelable