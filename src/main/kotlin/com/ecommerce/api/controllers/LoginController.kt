package com.ecommerce.api.controllers

import com.ecommerce.api.exception.GetException
import com.ecommerce.api.models.Admin
import com.ecommerce.api.models.LoginDTO
import com.ecommerce.api.models.LogoutDTO
import com.ecommerce.api.models.User
import com.ecommerce.api.service.AdminService
import com.ecommerce.api.service.LoginService
import com.ecommerce.api.service.UserService
import com.ecommerce.api.util.JwtUtils
import org.springframework.web.bind.annotation.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse


@RestController
@CrossOrigin(origins = arrayOf("https://ecommercefrontend17.herokuapp.com"), allowCredentials = "true")
class LoginController(
    var loginService: LoginService,
    var userService: UserService,
    var adminService: AdminService,
    var util: JwtUtils

){
    private  val jwtNull = "Email and password Not Found"
    private val invalidType = "Inavlid type"

    @PutMapping("/login")
    fun login(@RequestBody loginDTO: LoginDTO, response: HttpServletResponse): Any {
        return this.loginService.login(loginDTO.email, loginDTO.password, response)
    }

    @GetMapping("/responseuser")
    fun user(@CookieValue("User") jwt: String?): User {
        try {
            if (jwt == null) {
                throw GetException(jwtNull)
            }
            val body = util.verify(jwt)
            if (body["type"]?.equals("User") == true) {
                return this.userService.getUserById(body.issuer)
            }

            else{
                throw GetException(invalidType)
            }
        } catch (e: Exception) {
            throw GetException("unauthenticated")
        }
    }
    @GetMapping("/responseadmin")
    fun admin(@CookieValue("Admin") jwt: String?): Admin {
        try {
            if (jwt == null) {
                throw GetException(jwtNull)
            }
            val body = util.verify(jwt)
            if (body["type"]?.equals("Admin")== true) {
                return this.adminService.viewAdmin(body.issuer.toString())
            }
            else{
                throw GetException(invalidType)
            }
        } catch (e: Exception) {
            throw GetException("unauthenticated")
        }
    }
    @PutMapping("/logout")
    fun logout(@RequestBody logoutDTO: LogoutDTO, response: HttpServletResponse): String {

        val cookie = Cookie(logoutDTO.type, "")
        cookie.maxAge = 0
        response.addCookie(cookie)

        return this.loginService.logout(logoutDTO.email)
    }
}