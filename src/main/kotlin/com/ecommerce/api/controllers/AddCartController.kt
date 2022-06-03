package com.ecommerce.api.controllers

import com.ecommerce.api.DAO.OrderHistoryDAO
import com.ecommerce.api.models.Cart
import com.ecommerce.api.models.OrderHistory
import com.ecommerce.api.models.ProductAddedToCart
import com.ecommerce.api.models.TempProductCart
import com.ecommerce.api.service.ProductAddedToCartService
import com.ecommerce.api.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/review")
@CrossOrigin("http://localhost:3000")
class AddCartController(
    var userService: UserService,

    var productAddedToCartService: ProductAddedToCartService,
) {


    @PutMapping("/cart")
    fun addCart(@Valid @RequestBody tempProdCart: TempProductCart): Cart {

        return this.productAddedToCartService.addCart(

            tempProdCart.productId!!,

            tempProdCart.tempProduct
        )
    }

    @PutMapping("/useragree/{id}/{paythrough}/{paymentid}")
    fun agreePayment(
        @PathVariable id: String,
        @PathVariable paythrough: String,
        @PathVariable paymentid: String
    ): Cart = this.userService.changePaymentAgree(id, paythrough, paymentid)


    @PutMapping("/userHistory/{id}")
    fun agreeDelivery(@PathVariable id:String):OrderHistory = this.userService.changeDeliveryStatus(id)

    @PutMapping("/changeHistory/{id}")
    fun changeCart(@PathVariable id:String):OrderHistory = this.userService.changeCart(id)

//    @GetMapping("/cart")
//    fun getAll(): List<ProductAddedToCart> = this.productAddedToCartService.getAllCart()

    @GetMapping("/orderhistory")
    fun getAllCart(): List<Cart> = this.productAddedToCartService.getCart()

    @GetMapping("/gethistory")
    fun getAllHistroy(): List<OrderHistory> = this.productAddedToCartService.getHistory()



    @DeleteMapping("/{id}")
    fun deleteProductsById(@PathVariable id: String): String = this.productAddedToCartService.deleteProduct(id)

//    @DeleteMapping("/getCart/{cartid}/{id}")
//    fun deleteProductCartById(@PathVariable id: String): String = this.productAddedToCartService.deleteCartProduct(cartid,id)

    @GetMapping("/getCart/{id}")
    fun getCart(@PathVariable id: String):List<ProductAddedToCart>? = this.productAddedToCartService.getCartByProducts(id)

    @DeleteMapping("/deletebyId/{orderId}/{productId}")
    fun deleteById(@PathVariable orderId:String,@PathVariable productId:String):String = this.productAddedToCartService.deleteProductById(orderId,productId)
}