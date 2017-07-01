package com.yannshu.epicpicturesofearth.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class PictureMetadata {

    @PrimaryKey
    var image: String? = null

    var caption: String? = null

    var date: String? = null
}