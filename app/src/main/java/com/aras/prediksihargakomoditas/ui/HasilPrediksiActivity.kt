package com.aras.prediksihargakomoditas.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aras.prediksihargakomoditas.data.ResultState
import com.aras.prediksihargakomoditas.databinding.ActivityHasilPrediksiBinding

class HasilPrediksiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHasilPrediksiBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHasilPrediksiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObeservable()

        val x1 = intent.getStringExtra("X1")
        val x2 = intent.getStringExtra("X2")

        if (x1 != null && x2 != null) {
            getResultPredict(x1, x2)
        }
    }

    private fun initObeservable() {
        viewModel.predictionStatus.observe(this) {
            when (it) {
                is ResultState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is ResultState.Success -> {
                    val result = it.value
                    binding.progressBar.visibility = View.GONE
                    binding.tvResultPrice.text = result.toString()
                }
                is ResultState.Failure -> {
                    Toast.makeText(
                        this@HasilPrediksiActivity,
                        it.throwable.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun getResultPredict(x1: String, x2: String) {
        viewModel.getPrediction(x1.toInt(), x2.toInt())
    }
}