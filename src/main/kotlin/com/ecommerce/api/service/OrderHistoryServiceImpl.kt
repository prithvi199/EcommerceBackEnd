package com.ecommerce.api.service

import com.ecommerce.api.DAO.CartDAO
import com.ecommerce.api.DAO.OrderHistoryDAO
import com.ecommerce.api.exception.GetException
import com.ecommerce.api.models.Cart
import com.ecommerce.api.models.OrderHistory
import org.springframework.stereotype.Service

@Service
class OrderHistoryServiceImpl (
    var orderHistoryDAO: OrderHistoryDAO
):OrderHistoryService{

    override fun getOrderHistoryById(userEmail: String): OrderHistory {
        if (orderHistoryDAO.existsByUserEmail(userEmail)) return this.orderHistoryDAO.findById(userEmail).get()
        else throw GetException("Customer not found, Please register....")
    }
}