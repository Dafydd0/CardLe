package com.elteleco.hearthapp

import com.google.gson.annotations.SerializedName

data class CardsData (

    @SerializedName("id") var id: Int? = null,
    @SerializedName("collectible") var collectible: Int? = null,
    @SerializedName("slug") var slug: String? = null,
    @SerializedName("classId") var classId: Int? = null,
    @SerializedName("multiClassIds") var multiClassIds: ArrayList<String> = arrayListOf(),
    @SerializedName("minionTypeId") var minionTypeId: Int? = null,
    @SerializedName("cardTypeId") var cardTypeId: Int? = null,
    @SerializedName("cardSetId") var cardSetId: Int? = null,
    @SerializedName("rarityId") var rarityId: Int? = null,
    @SerializedName("artistName") var artistName: String? = null,
    @SerializedName("health") var health: Int? = null,
    @SerializedName("attack") var attack: Int? = null,
    @SerializedName("manaCost") var manaCost: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("text") var text: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("imageGold") var imageGold: String? = null,
    @SerializedName("flavorText") var flavorText: String? = null,
    @SerializedName("cropImage") var cropImage: String? = null,
    @SerializedName("keywordIds") var keywordIds: List<Int>? = null

)