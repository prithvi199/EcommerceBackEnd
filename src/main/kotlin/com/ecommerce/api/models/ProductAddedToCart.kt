package com.ecommerce.api.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id

data class ProductAddedToCart(
    @Id
    var id:String? = ObjectId().toString(),
    var productName:String?,
    var productQty:Int?,
    var productPrice:Double?
) {
    constructor() : this(null,null,0,0.0)
}