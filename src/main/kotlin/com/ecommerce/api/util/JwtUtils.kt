package com.ecommerce.api.util


import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.Cookie
import io.jsonwebtoken.SignatureAlgorithm
import javax.servlet.http.HttpServletResponse
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts

@Component
class JwtUtils {
    companion object {
        private const val secret = "This_is_secret"
        private const val expiryDuration = (60 * 60).toLong()
    }

    fun generateJwt(email:String, type:String,response: HttpServletResponse): String {
        val claims = Jwts.claims()
            .setIssuer(email)
        claims["type"] = type
        val jwt = Jwts.builder().setClaims(claims)



            .setExpiration(Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000)) // 1 day
            .signWith(SignatureAlgorithm.HS512, "Secret").compact()
        val cookie = Cookie(type, jwt)
        cookie.isHttpOnly =true
        response.addCookie(cookie)
        return jwt
    }

    fun verify(jwt:String?):Claims{
        return Jwts.parser().setSigningKey("Secret").parseClaimsJws(jwt).body
    }
}