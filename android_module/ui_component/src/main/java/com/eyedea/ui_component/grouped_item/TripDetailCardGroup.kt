package com.eyedea.ui_component.grouped_item

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.eyedea.ui_component.base.BaseRecyclerViewAdapter
import com.eyedea.ui_component.databinding.BaseGroupBinding
import com.eyedea.ui_component.item.TripDetailCardItem
import com.eyedea.ui_component.util.ResolutionUtils.dp

class TripDetailCardGroup(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs){

    private val binding = BaseGroupBinding.inflate(LayoutInflater.from(context), this, true)
    private val adapter = object : BaseRecyclerViewAdapter<TripDetailCardItem.Data, TripDetailCardItem>(
        RecyclerView.LayoutParams.WRAP_CONTENT
    ) {
        override fun ViewHolder<TripDetailCardItem>.onBind(
            item: TripDetailCardItem.Data,
            position: Int,
        ) {
            view.apply {
                title = item.title
                price = item.price
                rating = item.rating
                image = item.image
                isFav = item.isFav
                onFavButtonPressedCallback = { refreshAdapter(position) }
            }
        }

        override fun generateView(viewType: Int) = TripDetailCardItem(context)
    }

    var list = listOf<TripDetailCardItem.Data>()
        set(value) {
            field = value
            adapter.submitList(value)
        }

    var onFavButtonPressedCallback : (Int) -> Unit = {}
        set(value){
            field = value
            refreshView()
        }

    init {
        clipToPadding = false
        clipChildren = false
        binding.rv.apply {
            adapter = this@TripDetailCardGroup.adapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            itemAnimator = null
            addItemDecoration(object : ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.right = 10.dp(context)
                }
            })
        }

        list = List(5){ TripDetailCardItem.Data(
            title = "Title $it",
            price = "",
            rating = 0.0,
            image = null,
            isFav = false,
        ) }
    }

    private fun refreshAdapter(idx: Int){
        onFavButtonPressedCallback.invoke(idx)
        val newList = adapter.currentList.mapIndexed { index, data ->
            data.copy(
                isFav = if (index == idx) !data.isFav else data.isFav
            )
        }
        list = newList
    }

    private fun refreshView() {

    }

}