package com.aras.prediksihargakomoditas.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aras.prediksihargakomoditas.data.PredictionBody
import com.aras.prediksihargakomoditas.data.ResultState
import com.aras.prediksihargakomoditas.network.ApiConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _predictionStatus = MutableLiveData<ResultState<Int>>()
    val predictionStatus: LiveData<ResultState<Int>> = _predictionStatus

    fun getPrediction(x1: Int, x2: Int) {
        _predictionStatus.value = ResultState.Loading
        val body = PredictionBody(x1, x2)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = ApiConfig.getApiService().getPrediction(body)
                _predictionStatus.postValue(ResultState.Success(result.data.prediction!!))
            } catch (e: Exception) {
                _predictionStatus.postValue(ResultState.Failure(e))
            }
        }
    }
}