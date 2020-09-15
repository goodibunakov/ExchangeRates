package ru.goodibunakov.exchangerates.domain

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class FooterDecoration(private val footerDp: Float) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val adapter = parent.adapter ?: return
        when (parent.getChildAdapterPosition(view)) {
            adapter.itemCount - 1 -> outRect.bottom = (footerDp * view.context.resources.displayMetrics.density + 0.5f).toInt()
            else -> outRect.set(0, 0, 0, 0)
        }
    }
}