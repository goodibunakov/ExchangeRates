package ru.goodibunakov.exchangerates.presentation.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import ru.goodibunakov.exchangerates.App
import ru.goodibunakov.exchangerates.R
import ru.goodibunakov.exchangerates.presentation.adapter.CurrencyAdapter
import ru.goodibunakov.exchangerates.presentation.adapter.FooterDecoration
import ru.goodibunakov.exchangerates.presentation.model.CurrencyUi
import ru.goodibunakov.exchangerates.presentation.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels { App.viewModelFactory }
    private val adapter = CurrencyAdapter(::click)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecycler()
        viewModel.showLoadingLiveData.observe(
            this,
            { progress.visibility = if (it) View.VISIBLE else View.GONE })
        viewModel.dataLiveData.observe(this, { setData(it) })
        viewModel.errorLiveData.observe(this, {

        })
        viewModel.savedCurrencyLiveData.observe(this, {
            btnGo.isEnabled = it != null && it.charCode.isNotEmpty()
            code.text = it?.charCode
        })

        btnGo.setOnClickListener {
            if (enterEditText.text.isNullOrBlank()) {
                enterField.error = getString(R.string.error_empty_input)
            } else {
                enterField.error = null
                viewModel.calculate(enterEditText.text.toString())
            }
        }

        viewModel.resultLiveData.observe(this, { result.text = it })

        update.setOnClickListener { viewModel.update() }
    }

    private fun initRecycler() {
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
        recycler.addItemDecoration(FooterDecoration(8f))
    }

    private fun setData(list: List<CurrencyUi>) {
        adapter.submitList(list)
    }

    private fun click(item: CurrencyUi) {
        viewModel.saveCurrency(item)
    }
}