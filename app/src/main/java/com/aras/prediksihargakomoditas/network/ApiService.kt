package com.aras.prediksihargakomoditas.network

import com.aras.prediksihargakomoditas.data.PredictionBody
import com.aras.prediksihargakomoditas.data.PredictionResponse
import com.aras.prediksihargakomoditas.data.ResponseObject
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/predict")
    suspend fun getPrediction(
        @Body predictionBody: PredictionBody
    ): ResponseObject<PredictionResponse>
}