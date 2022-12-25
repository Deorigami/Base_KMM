package com.eyedea.ui_component.view.template

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.eyedea.ui_component.R
import com.eyedea.ui_component.databinding.BaseGroupBinding
import com.eyedea.ui_component.databinding.TemplateHeaderBinding
import com.eyedea.ui_component.view.util.ImageViewUtils.setCustomSource

class Header(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs){

    private val binding = TemplateHeaderBinding.inflate(LayoutInflater.from(context), this, true)
    var title = ""
        set(value){
            field = value
            binding.title.text = value
        }

    var hasRightIcon = false
        set(value){
            field = value
            binding.rightIcon.isVisible = hasRightIcon
        }

    var rightIconSrc : Any? = null
        set(value){
            field = value
            binding.rightIcon.setCustomSource(value)
        }


    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.Header)
        typedArray.run {
            title = getString(R.styleable.Header_title) ?: ""
            hasRightIcon = getBoolean(R.styleable.Header_hasRightIcon, false)
            rightIconSrc = getDrawable(R.styleable.Header_rightIcon)
        }
        typedArray.recycle()
    }


    private fun refreshView() {

    }
}