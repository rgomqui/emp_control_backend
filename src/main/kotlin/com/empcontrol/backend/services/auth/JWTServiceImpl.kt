package com.empcontrol.backend.services.auth

import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.time.LocalDate
import java.util.Date

@Service
class JWTServiceImpl(
    @Value("\${security.jwt.expiration-in-minutes}")
    private val MINUTES_EXPIRATION_TOKEN: Long,

    @Value("\${security.jwt.secret-key}")
    private val SECRET_KEY_TOKEN: String
): JWTService {
    override fun generateToken(userDetails: UserDetails, extraClaims: Map<String, Any>): String {
        val issuedAt = Date()
        val expiration = Date((MINUTES_EXPIRATION_TOKEN * 60 * 1000) + issuedAt.time)
        val jwt = Jwts.builder()
            .claims(extraClaims)
            .subject(userDetails.username)
            .issuedAt(issuedAt)
            .expiration(expiration)
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .signWith(generateKey(), SignatureAlgorithm.HS256)
            .compact()

        return jwt
    }

    private fun generateKey(): Key? {
        return Keys.hmacShaKeyFor(SECRET_KEY_TOKEN.toByteArray())
    }
}