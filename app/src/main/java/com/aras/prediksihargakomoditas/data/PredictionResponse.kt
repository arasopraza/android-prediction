package com.aras.prediksihargakomoditas.data

import com.google.gson.annotations.SerializedName

data class ResponseObject<T>(
	@field:SerializedName("data")
	val data: T,

	@field:SerializedName("message")
	val message: String? = null
)

data class PredictionResponse(

	@field:SerializedName("prediction")
	val prediction: Int? = null
)
