package com.ecommerce.api.DAO

import com.ecommerce.api.models.ProductAddedToCart
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductAddedToCartDAO : MongoRepository<ProductAddedToCart, String> {

     override fun existsById(id: String): Boolean

}