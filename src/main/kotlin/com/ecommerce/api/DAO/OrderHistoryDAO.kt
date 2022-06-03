package com.ecommerce.api.DAO


import com.ecommerce.api.models.Cart
import com.ecommerce.api.models.OrderHistory
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderHistoryDAO : MongoRepository<OrderHistory, String> {
    //    fun findByUserEmail(email: String): MutableList<Cart>
//    fun findById(id: String): Cart
    fun findByUserEmail(userEmail: String):OrderHistory
//    fun existsByProductId(productId: String): Boolean
    fun findByProduct(productId: String): OrderHistory
    fun existsByUserEmail(userEmail: String): Boolean
    //abstract fun changeDeliveryStatus(id: String): OrderHistory

}