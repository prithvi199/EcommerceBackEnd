package com.ecommerce.api.controllers

import com.ecommerce.api.models.Cart
import com.ecommerce.api.models.Product
import com.ecommerce.api.models.ProductAddedToCart
import com.ecommerce.api.service.CartService
import com.ecommerce.api.service.ProductAddedToCartService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/cart")
@CrossOrigin("https://ecommercefrontend17.herokuapp.com")
class CartController (
    var cartService: CartService,
    var productAddedToCartService: ProductAddedToCartService

        ){

    @GetMapping("/{userEmail}")
    fun getCartById(@PathVariable userEmail: String): Cart = this.cartService.getCartById(userEmail)

//    @GetMapping("/{userEmail}")
//    fun getCartById(@PathVariable userEmail: String): Cart = this.cartService.getCartById(userEmail)

    @GetMapping("/list/{userEmail}")
    fun getCart(@PathVariable userEmail: String):List<ProductAddedToCart>? = this.productAddedToCartService.getCartByProducts(userEmail)


}