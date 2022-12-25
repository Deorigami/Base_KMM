package com.eyedea.ui_component.view.template

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.eyedea.ui_component.databinding.TemplateTextBoxBinding

class TextBox(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs){
    val binding = TemplateTextBoxBinding.inflate(LayoutInflater.from(context), this, true)
}