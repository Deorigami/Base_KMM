package com.eyedea.ui_component.template

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.eyedea.ui_component.databinding.BaseGroupBinding
import com.eyedea.ui_component.databinding.TemplateHeaderBinding

class Header(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs){
    private val binding = TemplateHeaderBinding.inflate(LayoutInflater.from(context), this, true)

}