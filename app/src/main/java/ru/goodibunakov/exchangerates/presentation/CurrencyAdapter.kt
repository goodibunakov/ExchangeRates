package ru.goodibunakov.exchangerates.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item.view.*
import ru.goodibunakov.exchangerates.R
import ru.goodibunakov.exchangerates.domain.Currency

class CurrencyAdapter(
    private val clickListener: (String) -> Unit
) : ListAdapter<Currency, CurrencyAdapter.CurrencyViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder =
        CurrencyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false),
            clickListener
        )

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class CurrencyViewHolder(
        override val containerView: View,
        private val clickListener: (String) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: Currency) {
            containerView.title.text = item.name
            containerView.code.text = item.charCode
            containerView.setOnClickListener { clickListener(item.id) }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Currency>() {
        override fun areItemsTheSame(oldItem: Currency, newItem: Currency) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Currency, newItem: Currency) =
            oldItem.id == newItem.id
    }
}