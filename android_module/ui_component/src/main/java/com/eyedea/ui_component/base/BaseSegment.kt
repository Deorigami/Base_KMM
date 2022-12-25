package com.eyedea.ui_component.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.updatePadding
import com.eyedea.ui_component.R
import com.eyedea.ui_component.util.ResolutionUtils.convertDpToPx
import com.eyedea.ui_component.databinding.BaseSegmentBinding

class BaseSegment(
    context: Context,
    attributeSet: AttributeSet? = null
) : LinearLayout(context, attributeSet){

    private val binding = BaseSegmentBinding.inflate(LayoutInflater.from(context), this, true)

    var title = ""
        set(value) {
            field = value
            refreshView()
        }

    var withMargin : Boolean = true
        set(value) {
            field = value
            refreshView()
        }

    init {
        clipChildren = false
        clipToPadding = false
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.BaseSegment)
        typedArray.run {
            title = getString(R.styleable.BaseSegment_title) ?: ""
            withMargin = getBoolean(R.styleable.BaseSegment_withMargin, true)
        }
        typedArray.recycle()
        orientation = VERTICAL
        binding.containerView.background = this.background
    }


    override fun onFinishInflate() {
        super.onFinishInflate()
        repeat(childCount){
            if (it > 0){
                val view = getChildAt(1)
                removeViewAt(1)
                binding.containerView.addView(view)
                if (title.isEmpty()) {
                    title = view::class.java.simpleName
                }
            }
        }
    }

    private fun refreshView(){
        binding.titleView.text = title
        if (withMargin){
            binding.containerView.updatePadding((16).convertDpToPx(context).toInt(),(5).convertDpToPx(context).toInt(),(16).convertDpToPx(context).toInt(),(5).convertDpToPx(context).toInt())
        } else binding.containerView.updatePadding(0,0,0,0)
    }
}