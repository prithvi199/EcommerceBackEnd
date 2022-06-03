package com.ecommerce.api.controllers

import com.ecommerce.api.models.Cart
import com.ecommerce.api.models.OrderHistory
import com.ecommerce.api.service.CartService
import com.ecommerce.api.service.OrderHistoryService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orderhistory")
@CrossOrigin("http://localhost:3000")
class OrderHistoryController (
    var orderHistoryService: OrderHistoryService


){

    @GetMapping("/{userEmail}")
    fun getCartById(@PathVariable userEmail: String): OrderHistory = this.orderHistoryService.getOrderHistoryById(userEmail)





}