package com.ecommerce.api.models

import org.springframework.web.multipart.MultipartFile
import javax.validation.constraints.NotNull

data class ProductImageDTO (

    val name:String,

    var quantity:Int,


    val price:Double,

    val description:String,


    var productImage:MultipartFile
)