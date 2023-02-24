package com.sardordev.burgerapp.domain

import com.bumptech.glide.load.engine.Resource
import com.sardordev.burgerapp.data.model.BurgerModel
import com.sardordev.burgerapp.data.model.BurgerModelItem
import com.sardordev.burgerapp.utils.ResourceEvent

interface BurgerRepository {

    suspend fun getBurgerList(): ResourceEvent<List<BurgerModelItem>>

}