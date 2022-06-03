package com.ecommerce.api.service

import com.ecommerce.api.models.Cart
import com.ecommerce.api.models.OrderHistory
import com.ecommerce.api.models.User

interface UserService {
    fun addUser(user: User): User
    fun getUserById(email: String): User
    fun getAllUser(): List<User>
    fun deleteById(email: String): String
    fun changePaymentAgree(id: String, payThrough: String, paymentID: String): Cart
    fun changeDeliveryStatus(id:String):OrderHistory
    fun changeCart(id:String):OrderHistory

}