package com.sardordev.burgerapp.domain

import com.bumptech.glide.load.engine.Resource
import com.sardordev.burgerapp.data.api.BurgerApi
import com.sardordev.burgerapp.data.model.BurgerModel
import com.sardordev.burgerapp.data.model.BurgerModelItem
import com.sardordev.burgerapp.utils.ResourceEvent
import javax.inject.Inject


class BurgerRepositoryImp @Inject constructor(private val api: BurgerApi) : BurgerRepository {
    override suspend fun getBurgerList(): ResourceEvent<List<BurgerModelItem>> {
        return try {
            val result = api.getburgersList()
            if (result.isSuccessful && result.body() != null) {
                ResourceEvent.Success(result.body())
            } else {
                ResourceEvent.Error(message = result.message())
            }
        } catch (e: Exception) {
            ResourceEvent.Error(message = e.message)
        }
    }
}
