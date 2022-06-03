package com.ecommerce.api.service

import com.ecommerce.api.models.Cart
import com.ecommerce.api.models.OrderHistory
import com.ecommerce.api.models.ProductAddedToCart
import com.ecommerce.api.models.TempProduct

interface ProductAddedToCartService {

    fun addCart(

        productID: String,

        product: MutableList<TempProduct>?
    ): Cart

    fun deleteProduct(id: String): String
    //fun deleteCartProduct(cid:String,id: String): String

//    fun getAllCart(): List<ProductAddedToCart>

    fun getCart(): List<Cart>

    fun getHistory(): List<OrderHistory>

    fun getCartByProducts(id: String):List<ProductAddedToCart>?

    fun deleteProductById(orderId:String,productId: String):String
}