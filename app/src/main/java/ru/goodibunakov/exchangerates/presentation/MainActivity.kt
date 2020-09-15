package ru.goodibunakov.exchangerates.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import ru.goodibunakov.exchangerates.App
import ru.goodibunakov.exchangerates.R
import ru.goodibunakov.exchangerates.domain.Currency
import ru.goodibunakov.exchangerates.domain.FooterDecoration

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels { App.viewModelFactory }
    private val adapter = CurrencyAdapter(::click)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecycler()
        viewModel.dataLiveData.observe(this, { setData(it) })
        viewModel.errorLiveData.observe(this, { })
    }

    private fun initRecycler() {
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
        recycler.addItemDecoration(FooterDecoration( 8f))
    }

    private fun setData(list: List<Currency>) {
        adapter.submitList(list)
    }

    private fun click(id: String) {
        Toast.makeText(this, "Clicked id = $id", Toast.LENGTH_LONG).show()
    }
}