package br.com.allnerdbrasil.mvvmpratico.model

import android.media.ThumbnailUtils
import retrofit2.http.Url

data class Live(
    var title: String,
    var author: String,
    var thumbnailUrl: String,
    var link: String,

)
