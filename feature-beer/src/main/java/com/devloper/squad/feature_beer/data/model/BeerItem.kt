package com.devloper.squad.feature_beer.data.model

import com.google.gson.annotations.SerializedName

data class BeerItem(
  @SerializedName("description")
  val description: String = "",
  @SerializedName("id")
  val id: Int = 0,
  @SerializedName("image_url")
  val imageUrl: String = "",
  @SerializedName("name")
  val name: String = "",
)