package com.eyedea.ui_component.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.eyedea.ui_component.databinding.ComponentHeaderBinding

class ComponentHeader(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs){
    private val binding = ComponentHeaderBinding.inflate(LayoutInflater.from(context), this, true)


}