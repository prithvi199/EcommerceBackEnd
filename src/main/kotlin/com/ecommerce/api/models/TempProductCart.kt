package com.ecommerce.api.models

import org.bson.types.ObjectId

class TempProductCart(


    var productId:String? = ObjectId().toString(),

    var tempProduct:MutableList<TempProduct>? = mutableListOf()
)