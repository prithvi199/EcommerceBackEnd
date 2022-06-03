package com.ecommerce.api.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Document(collection="cart_product")
data class Product (
    @Id
    val prodId:String? = ObjectId().toString(),

    @field:NotBlank(message = "Product name can't be blank, it's mandatory")
    val name:String,
    @field:NotNull(message = "Mandatory, Only digits accepted")
    var quantity:Int,

    @field:NotNull(message = "Mandatory, Only digits accepted")
    val price:Double,

    @field:NotBlank(message = "Product description can't be blank, it's mandatory")
    val description:String,

    @field:NotNull(message = "Mandatory")
    var image:String?

)