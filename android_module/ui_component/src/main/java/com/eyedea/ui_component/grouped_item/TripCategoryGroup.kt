package com.eyedea.ui_component.grouped_item

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.eyedea.ui_component.base.BaseRecyclerViewAdapter
import com.eyedea.ui_component.databinding.BaseGroupBinding
import com.eyedea.ui_component.item.TripCategoryItem
import com.eyedea.ui_component.util.ResolutionUtils.convertDpToPx
import kotlin.math.roundToInt

class TripCategoryGroup(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs){

    private val binding = BaseGroupBinding.inflate(LayoutInflater.from(context), this, true)
    private val rvAdapter = object : BaseRecyclerViewAdapter<TripCategoryItem.Data, TripCategoryItem>(
        RecyclerView.LayoutParams.WRAP_CONTENT
    ) {
        override fun ViewHolder<TripCategoryItem>.onBind(
            item: TripCategoryItem.Data,
            position: Int,
        ) {
            view.apply {
                data = item
                onCardPressedCallback = { refreshAdapter(position) }
            }
        }

        override fun generateView(viewType: Int): TripCategoryItem = TripCategoryItem(context)
    }

    var list = listOf<TripCategoryItem.Data>()
        set(value){
            field = value
            rvAdapter.submitList(value)
        }

    var onCardPressedCallback : (Int) -> Unit = {}
        set(value){
            field = value
            refreshView()
        }

    init {
        clipChildren = false
        clipToPadding = false
        binding.rv.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            itemAnimator = null
            addItemDecoration(object : ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.right = 10.convertDpToPx(context).roundToInt()
                }
            })
        }

        binding.componentHeader.isVisible = true

        list = List(4){ TripCategoryItem.Data(
            title = "Title $it"
        ) }
    }

    private fun refreshAdapter(idx : Int){
        onCardPressedCallback.invoke(idx)
        list = list.mapIndexed { index, data ->
            data.copy(
                isActive = index == idx
            )
        }
    }

    private fun refreshView() {

    }

}