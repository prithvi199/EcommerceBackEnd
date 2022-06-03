package com.ecommerce.api.service

import javax.servlet.http.HttpServletResponse

interface LoginService {
    fun login(email: String, password: String, response: HttpServletResponse): Any

    fun logout(email: String): String
}