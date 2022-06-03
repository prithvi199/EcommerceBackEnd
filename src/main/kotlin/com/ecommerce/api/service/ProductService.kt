package com.ecommerce.api.service

import com.ecommerce.api.models.Product
import org.springframework.web.multipart.MultipartFile


interface ProductService {
    fun addProduct(file: MultipartFile,name: String, price: Double, quantity:Int,description:String): Product

    fun viewProductById(productId: String): Product
    fun viewProductByName(name: String): List<Product>
    fun viewAllProducts(): MutableList<Product>
    fun deleteProduct(productId: String): String

}