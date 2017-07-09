package com.yannshu.epicpicturesofearth.view.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.yannshu.epicpicturesofearth.R
import com.yannshu.epicpicturesofearth.data.model.PictureMetadata
import com.yannshu.epicpicturesofearth.utils.PictureUrlBuilder


class PicturesAdapter(context: Context, pictureUrlBuilder: PictureUrlBuilder): RecyclerView.Adapter<PicturesAdapter.ViewHolder>() {

    val mContext: Context = context

    val mPicturesUrlBuilder: PictureUrlBuilder = pictureUrlBuilder

    var mData: List<PictureMetadata>? = null

    override fun getItemCount(): Int {
        val data = mData
        if (data != null) {
            return data.size
        } else {
            return 0
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pictureMetadata: PictureMetadata? = mData?.get(position)
        if (pictureMetadata != null) {
            holder.bind(pictureMetadata)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_picture, parent, false)
        return ViewHolder(view, mPicturesUrlBuilder)
    }

    class ViewHolder(itemView: View, pictureUrlBuilder: PictureUrlBuilder): RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.picture_image_view)
        lateinit var mPictureImageView: ImageView

        @BindView(R.id.date_text_view)
        lateinit var mDateTextView: TextView

        val mPictureUrlBuilder: PictureUrlBuilder = pictureUrlBuilder

        init {
            ButterKnife.bind(this, itemView)
        }

        fun bind(pictureMetadata: PictureMetadata) {
            if (mPictureImageView.width == 0 || mPictureImageView.height == 0) {
                mPictureImageView.post(Runnable {
                    mPictureImageView.layoutParams.height = mPictureImageView.width
                    loadImage(pictureMetadata)
                })
            } else {
                loadImage(pictureMetadata)
            }
            mDateTextView.text = pictureMetadata.date
        }

        private fun loadImage(pictureMetadata: PictureMetadata) {
            Glide.with(itemView)
                    .load(mPictureUrlBuilder.buildUrl(pictureMetadata))
                    .into(mPictureImageView)
        }
    }
}