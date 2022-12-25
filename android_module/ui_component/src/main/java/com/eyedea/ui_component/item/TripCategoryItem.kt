package com.eyedea.ui_component.item

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.eyedea.ui_component.R
import com.eyedea.ui_component.databinding.TripCategoryItemBinding

class TripCategoryItem(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs){
    private val binding = TripCategoryItemBinding.inflate(LayoutInflater.from(context), this, true)

    data class Data(
        val title : String,
        val image : Any? = null,
        val isActive : Boolean = false
    )

    var data : Data? = null
        set(value){
            field = value
            value?.let {
                title = it.title
                image = it.image
                isActive = it.isActive
            }
        }

    var title : String = ""
        set(value) {
            field = value
            binding.categoryText.text = value
            refreshView()
        }
    var image : Any? = null
        set(value) {
            field = value
            refreshView()
        }

    var isActive = false
        set(value){
            field = value
            refreshView()
        }

    var onCardPressedCallback : () -> Unit = {}
        set(value){
            field = value
            refreshView()
        }


    private fun refreshView(){
        binding.root.apply {
            setOnClickListener {
                onCardPressedCallback.invoke()
            }
            strokeColor = ContextCompat.getColor(context, if (isActive) R.color.primary_success_600 else R.color.primary_black_10)
        }
    }

}