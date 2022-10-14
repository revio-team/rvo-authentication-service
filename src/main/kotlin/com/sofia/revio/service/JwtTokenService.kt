package com.sofia.revio.service

import com.sofia.revio.model.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
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
        claims["active"] = user.active
        claims["scope"] = user.scope

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(user.id)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
            .signWith(SignatureAlgorithm.HS512, secret).compact()
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
