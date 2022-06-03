package com.ecommerce.api.controllers

import com.ecommerce.api.models.Cart
import com.ecommerce.api.models.Product
import com.ecommerce.api.models.User

import com.ecommerce.api.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.Optional
import javax.validation.Valid


@RestController
@RequestMapping( "/User")
@CrossOrigin(origins = arrayOf("https://ecommercefrontend17.herokuapp.com"), allowCredentials = "true")
class UserController( private var userService: UserService) {
    @PostMapping
    fun addUser(@Valid @RequestBody user: User): ResponseEntity<User> {
        return ResponseEntity(this.userService.addUser(user), HttpStatus.OK)
    }

    @GetMapping("/{email}")
    fun getUser(@PathVariable email: String): User = this.userService.getUserById(email)

    @GetMapping
    fun getAllUser(): List<User> {
        return this.userService.getAllUser()
    }



    @DeleteMapping("/{email}")
    fun deleteUser(@PathVariable email: String): String = this.userService.deleteById(email)





}