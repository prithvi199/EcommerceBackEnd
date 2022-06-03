package com.ecommerce.api.DAO

import com.ecommerce.api.models.Product
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository


@Repository
interface ProductDAO : MongoRepository<Product, String> {

    @Query("{'name' : ?0 }")
    fun findProductByName(name: String): kotlin.collections.List<Product>
    fun existsByProdId(prodId: String): Boolean
    fun existsProductByName(name: String): Boolean
    fun existsByName(name: String): Boolean
//    fun findProductsByProductName(name: String): Product
//
//    fun countByQuantity(quantity:Int):Long


}