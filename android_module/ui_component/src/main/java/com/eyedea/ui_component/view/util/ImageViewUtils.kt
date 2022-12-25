package com.eyedea.ui_component.view.util

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

object ImageViewUtils {
    fun ImageView.setCustomSource(
        source : Any?,
        invalidate : Boolean = false,
        saveCache: Boolean = true,
        defaultErrorImageUrl : Drawable? = null
    ){
        when(source){
            is String -> {
                if (invalidate) {
                    val picasso = Picasso.get()
                    picasso.load(source.ifEmpty { "null" }).noFade().noPlaceholder().setMemoryPolicy(saveCache).into(this)
                    picasso.invalidate(source.ifEmpty { "null" })
                } else {
                    if (source.isNotEmpty()) Picasso.get().load(source).setMemoryPolicy(true).into(this,
                        object : Callback {
                            override fun onSuccess() {

                            }

                            override fun onError(e: java.lang.Exception?) {
                                defaultErrorImageUrl?.let {
                                    this@setCustomSource.setImageDrawable(it)
                                }
                            }
                        })
                }
            }
            is Int -> {
                try {
                    this.setImageDrawable(ContextCompat.getDrawable(context, source))
                }catch (e : Exception){

                }
            }
            is Drawable -> { this.setImageDrawable(source) }
            is Bitmap -> this.setImageBitmap(source)
        }
    }

    private fun RequestCreator.setMemoryPolicy(saveCache : Boolean): RequestCreator {
        return if (!saveCache) memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE) else this
    }
}