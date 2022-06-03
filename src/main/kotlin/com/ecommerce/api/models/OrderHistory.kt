package com.ecommerce.api.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank


@Document(collection="original_orderHistory")
data class OrderHistory (
    @Id
    @field:Email(message = "Email should be valid")
    var userEmail:String,

    @field:NotBlank(message = "Name can't be blank, it's mandatory")
    var name:String,

    var product: Product?,
    var deliveryStatus:String? ="Not Delivered",
    //var cart:Cart?,
    var products: MutableList<ProductAddedToCart>? = mutableListOf<ProductAddedToCart>(),

    )


