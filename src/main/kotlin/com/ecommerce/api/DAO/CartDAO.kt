package com.ecommerce.api.DAO

import com.ecommerce.api.models.Cart
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CartDAO : MongoRepository<Cart, String> {
   fun findByUserEmail(email: String): Cart
   // fun findById(id: String): Cart
//    fun existsByProductId(productId: String): Boolean

   fun existsByUserEmail(userEmail: String): Boolean
fun findByProduct(productId: String): Cart

}