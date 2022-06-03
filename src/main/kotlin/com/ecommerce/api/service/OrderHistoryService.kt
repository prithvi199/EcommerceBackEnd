package com.ecommerce.api.service

import com.ecommerce.api.models.Cart
import com.ecommerce.api.models.OrderHistory

interface OrderHistoryService {

    fun getOrderHistoryById(userEmail: String): OrderHistory
}