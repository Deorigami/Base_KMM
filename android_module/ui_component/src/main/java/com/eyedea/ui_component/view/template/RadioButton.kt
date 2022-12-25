package com.eyedea.ui_component.view.template

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.eyedea.ui_component.R
import com.eyedea.ui_component.databinding.BaseGroupBinding
import com.eyedea.ui_component.databinding.RadioButtonBinding
import com.eyedea.ui_component.view.util.ImageViewUtils.setCustomSource

class RadioButton(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs){
    private val binding = RadioButtonBinding.inflate(LayoutInflater.from(context), this, true)

    var isActive = false
        set(value) {
            field = value
            binding.root.setCustomSource(
                if (isActive) ContextCompat.getDrawable(context,
                    R.drawable.ic_selected_radio_button) else ContextCompat.getDrawable(context,
                    R.drawable.ic_radio_button)
            )
        }

    init {
        isActive = true
    }
}