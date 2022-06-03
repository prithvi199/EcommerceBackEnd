package com.ecommerce.api.service

import com.ecommerce.api.models.Cart

interface CartService {

    fun getCartById(userEmail: String): Cart
}