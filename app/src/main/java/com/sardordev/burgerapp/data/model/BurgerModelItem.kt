package com.sardordev.burgerapp.data.model

data class BurgerModelItem(
    val desc: String? = null,
    val id: Int? = null,
    val images: List<Image>? = null,
    val ingredients: List<Ingredient>? = null,
    val name: String? = null,
    val price: Double? = null,
    val veg: Boolean? = null
)