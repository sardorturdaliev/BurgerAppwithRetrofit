package com.sardordev.burgerapp.utils

import com.bumptech.glide.load.engine.Resource
import com.sardordev.burgerapp.data.model.BurgerModel

sealed class ResourceEvent<T>(data: T?, message: String? = null) {

    data class Success<T>(val data: T?) : ResourceEvent<T>(data)
    data class Error<T>(val data: T? = null, val message: String?) : ResourceEvent<T>(data, message)

}
