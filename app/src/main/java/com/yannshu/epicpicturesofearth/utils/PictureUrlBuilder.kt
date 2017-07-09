package com.yannshu.epicpicturesofearth.utils

import com.yannshu.epicpicturesofearth.BuildConfig
import com.yannshu.epicpicturesofearth.data.model.PictureMetadata
import java.io.File

class PictureUrlBuilder {
    object Constants {
        val ARCHIVE: String = "archive"
        val DOT: String = "."
        val DASH: String = "-"
        val JPG: String = "jpg"
        val DATE_LENGTH: Int = 10
    }

    fun buildUrl(pictureMetadata: PictureMetadata): String {
        var builder: StringBuilder = StringBuilder()
        builder.append(BuildConfig.EPIC_URL)
        builder.append(File.separatorChar)
        builder.append(Constants.ARCHIVE)
        builder.append(File.separatorChar)
        builder.append(pictureMetadata.quality)
        builder.append(File.separatorChar)
        builder.append(pictureMetadata.date?.substring(0, Constants.DATE_LENGTH)?.replace(Constants.DASH, File.separator))
        builder.append(File.separatorChar)
        builder.append(Constants.JPG)
        builder.append(File.separatorChar)
        builder.append(pictureMetadata.image)
        builder.append(Constants.DOT)
        builder.append(Constants.JPG)
        return builder.toString()
    }
}