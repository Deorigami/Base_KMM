package com.eyedea.ui_component.view.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.eyedea.ui_component.databinding.ComponentHeaderBinding

class ComponentHeader(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs){
    private val binding = ComponentHeaderBinding.inflate(LayoutInflater.from(context), this, true)

    var leftTitle = ""
        set(value){
            field = value
            binding.componentHeaderLeftText.text = value
        }
    var rightTitle = ""
        set(value){
            field = value
            binding.componentHeaderRightText.text = value
        }
    var hasRightText = true
        set(value){
            field = value
            binding.componentHeaderRightText.isVisible = value
        }


}