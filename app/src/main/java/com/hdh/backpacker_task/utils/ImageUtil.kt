package com.hdh.backpacker_task.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.target.ViewTarget
import com.bumptech.glide.signature.ObjectKey

object ImageUtil {
    fun getImage(
        imageView : AppCompatImageView ,
        imageUrl : String ,
        isAlphaAnimation : Boolean = true
    ) : ViewTarget<ImageView, Drawable> {
        return Glide.with(imageView.context).load(imageUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean = false
                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    if (isAlphaAnimation){
                        AnimationUtil.setAlphaAnimation(imageView)
                    }
                    return false
                }
            })
            .placeholder(ColorDrawable(Color.WHITE))
            .signature(ObjectKey(imageUrl))
            .into(imageView)
    }
}