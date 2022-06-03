package com.ecommerce.api.exception

import org.springframework.http.HttpStatus

data class ExceptionErrorCollection (

    var message:String?,
    var status: HttpStatus= HttpStatus.BAD_REQUEST,
    var code:Int =status.value()

        )
