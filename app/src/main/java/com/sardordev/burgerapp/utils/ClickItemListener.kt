package com.sardordev.burgerapp.utils

import com.sardordev.burgerapp.data.model.BurgerModelItem

interface ClickItemListener {
    fun clickItem(burgerModelItem: BurgerModelItem)

}