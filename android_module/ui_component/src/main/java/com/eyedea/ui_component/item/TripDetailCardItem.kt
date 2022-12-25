package com.eyedea.ui_component.item

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.eyedea.ui_component.R
import com.eyedea.ui_component.databinding.BaseGroupBinding
import com.eyedea.ui_component.databinding.TripDetailCardItemBinding
import com.eyedea.ui_component.util.ImageViewUtils.setCustomSource

class TripDetailCardItem(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs){
    private val binding = TripDetailCardItemBinding.inflate(LayoutInflater.from(context), this, true)

    data class Data(
        val title : String = "",
        val price : String = "",
        val rating : Double = 0.0,
        val image : Any? = null,
        val isFav : Boolean = false
    )

    var title : String = ""
        set(value) {
            field = value
            refreshView()
        }
    var price : String = ""
        set(value) {
            field = value
            refreshView()
        }
    var rating : Double = 0.0
        set(value) {
            field = value
            refreshView()
        }
    var image : Any? = null
        set(value) {
            field = value
            refreshView()
        }
    var isFav : Boolean = false
        set(value) {
            field = value
            refreshView()
        }

    var onFavButtonPressedCallback : () -> Unit = {}
        set(value){
            field = value
            refreshView()
        }


    private fun refreshView() {
        binding.likeContainer.setOnClickListener { onFavButtonPressedCallback.invoke() }
        binding.likeIcon.setCustomSource(ContextCompat.getDrawable(
            context,
            if (isFav) R.drawable.ic_heart_filler else R.drawable.ic_heart
        ))
        binding.title.text = title
    }

}