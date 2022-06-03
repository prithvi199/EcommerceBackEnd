package com.ecommerce.api.service

import com.ecommerce.api.DAO.CartDAO
import com.ecommerce.api.exception.GetException
import com.ecommerce.api.models.Cart
import org.springframework.stereotype.Service


@Service
class CartServiceImpl (
     var cartDAO: CartDAO
        ):CartService{

    override fun getCartById(userEmail: String): Cart {
        if (cartDAO.existsByUserEmail(userEmail)) return this.cartDAO.findById(userEmail).get()
        else throw GetException("Customer not found, Please register....")
    }
}