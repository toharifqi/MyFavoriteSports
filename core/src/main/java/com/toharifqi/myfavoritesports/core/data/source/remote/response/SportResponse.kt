package com.toharifqi.myfavoritesports.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SportsListResponse(

	@field:SerializedName("sports")
	val sports: List<SportsItemResponse>
)

data class SportsItemResponse(

	@field:SerializedName("idSport")
	val idSport: String,

	@field:SerializedName("strFormat")
	val strFormat: String,

	@field:SerializedName("strSport")
	val strSport: String,

	@field:SerializedName("strSportThumb")
	val strSportThumb: String,

	@field:SerializedName("strSportThumbGreen")
	val strSportThumbGreen: String,

	@field:SerializedName("strSportDescription")
	val strSportDescription: String
)
