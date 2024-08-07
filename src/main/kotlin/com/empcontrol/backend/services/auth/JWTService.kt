package com.empcontrol.backend.services.auth

import org.springframework.security.core.userdetails.UserDetails

interface JWTService {
    abstract fun generateToken(userDetails: UserDetails, extraClaims: Map<String, Any>): String
    abstract fun extractUsername(jwt: String): String
}