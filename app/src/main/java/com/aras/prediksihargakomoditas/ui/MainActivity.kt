package com.aras.prediksihargakomoditas.ui

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.aras.prediksihargakomoditas.R
import com.aras.prediksihargakomoditas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var listJenisKomoditas =
        arrayOf("Pilih Komoditas", "Bawang Merah", "Cabai Merah", "Cabai Rawit")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinner()

        binding.apply {
            tvGuide.setOnClickListener {
                basicAlert(this@MainActivity)
            }

            btnProccess.setOnClickListener {
                val x1 = etCurahHujan.text.toString().trim()
                val x2 = etProduksi.text.toString().trim()
                if (x1.isEmpty() || x1 == "") {
                    Toast.makeText(
                        this@MainActivity,
                        "Curah Hujan Tidak Boleh Kosong",
                        Toast.LENGTH_SHORT
                    ).show()
                    etCurahHujan.requestFocus()
                } else if (x1.toInt() > 500) {
                    Toast.makeText(
                        this@MainActivity,
                        "Curah Hujan Tidak Boleh Melebihi Range",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (x2.isEmpty() || x2 == "") {
                    Toast.makeText(
                        this@MainActivity,
                        "Produksi Tidak Boleh Kosong",
                        Toast.LENGTH_SHORT
                    ).show()
                    etProduksi.requestFocus()
                } else {
                    val intent = Intent(this@MainActivity, HasilPrediksiActivity::class.java)
                    intent.putExtra("X1", x1)
                    intent.putExtra("X2", x2)
                    startActivity(intent)
                }
            }
        }
    }

    private fun setupSpinner() {
        val spinnerKomoditas =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, listJenisKomoditas)
        spinnerKomoditas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerKomoditas.adapter = spinnerKomoditas
    }

    fun basicAlert(context: Context) {
        val dialogView: View = LayoutInflater.from(context).inflate(R.layout.dialog, null)

        val positiveButtonClick = { _: DialogInterface, _: Int ->
        }
        val negativeButtonClick = { _: DialogInterface, _: Int ->
        }

        val builder = AlertDialog.Builder(context)
        with(builder) {
            setTitle("Range Curah Hujan")
            setView(dialogView)
            setPositiveButton(
                "OK", DialogInterface.OnClickListener(function = positiveButtonClick)
            )
            setNegativeButton("CANCEL", negativeButtonClick)
            show()
        }
    }
}