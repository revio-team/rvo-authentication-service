package com.sofia.revio.service

import com.sofia.revio.model.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtTokenService(
    @Value("\${jwt.secret}") val secret: String
) {

    fun generateToken(user: User): String {
        val claims = HashMap<String, Any?>()
        claims["email"] = user.email
        claims["username"] = user.username
        claims["active"] = user.active
        claims["scope"] = user.scope

        return Jwts.builder().setClaims(claims).setSubject(user.id).setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
            .signWith(SignatureAlgorithm.HS512, secret).compact()
    }

    fun validateToken(token: String, user: User): Boolean {
        val claims = getClaimsFromToken(token)
        val validations = hashMapOf<String, Boolean>()

        if (user.id == claims.subject) {
            validations["subject"] = true
        }

        if (user.username == claims["username"]) {
            validations["username"] = true

        }

        for (validation in validations.values) {
            if (!validation) throw Exception("Invalid token")
        }
        return true
    }

    fun getUsernameFromToken(token:String): String {
        return getClaimsFromToken(token)["username"].toString()
    }

    private fun getClaimsFromToken(token: String): Claims {
        val key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret))
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body
    }

    companion object {
        private const val days = 5
        private const val hours = 24
        private const val minutes = 60
        private const val seconds = 60
        private const val millisseconds = 1000
        const val JWT_TOKEN_VALIDITY = days * hours * minutes * seconds * millisseconds
    }

}
