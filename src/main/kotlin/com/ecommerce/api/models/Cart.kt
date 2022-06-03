package com.ecommerce.api.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull


@Document(collection="original_cart")
data class Cart(


    @Id
    @field:Email(message = "Email should be valid")
    var userEmail:String,


    var product: Product?,



    @field:NotBlank(message = "Product name can't be blank, it's mandatory")
    val Productname:String,

    @field:NotNull(message = "Mandatory, Only digits accepted")
    var Productquantity: Int,
    @field:NotNull(message = "Mandatory, Only digits accepted")
    val Productprice:Int,
    var count:Int =0,
    var totalPay:Double=0.0,
    var shipmentStatus:String? ="Waiting for review",
    var agree:String ="Nothing",
    var paid:String ="Pending",
    var payThrough:String?,
    var paymentID:String?,

    @field:NotNull(message = "Mandatory, Only digits accepted")
    var image:String?,
    var products: MutableList<ProductAddedToCart>? = mutableListOf<ProductAddedToCart>(),


//    @DBRef
//    var products: MutableList<Product>? =mutableListOf<Product>()
)