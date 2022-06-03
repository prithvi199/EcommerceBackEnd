package com.ecommerce.api.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.function.Consumer

@RestControllerAdvice
@CrossOrigin("https://ecommercefrontend17.herokuapp.com")
class ExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun foundExceptionHandler(exc: AddException): String {
        return exc.message!!
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun getExceptionHandler(exc: GetException): String {
        return exc.message!!
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun getAllExceptionHandler(exc: GetAllException): String {
        return exc.message!!
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun deleteExceptionHandler(exc: DeleteException): String {
        return exc.message!!
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun nullExceptionHandler(exc: NullException): String {
        return exc.message!!
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun lockedExceptionHandler(exc: LockedException): String {
        return exc.message!!
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun unauthorizedExceptionHandler(exc: UnauthorizedException): String {
        return exc.message!!
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun generalExceptionHandler(exc: Exception): String {
        return exc.message!!
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(
        ex: MethodArgumentNotValidException
    ): String {
        val errors: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.forEach(Consumer { error: ObjectError ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.getDefaultMessage()
            errors[fieldName] = errorMessage
        })
        return errors.toString()
    }

}