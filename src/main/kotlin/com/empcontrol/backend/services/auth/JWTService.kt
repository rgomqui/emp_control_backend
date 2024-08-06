package com.empcontrol.backend.services.auth

import org.springframework.security.core.userdetails.UserDetails

interface JWTService {
    fun generateToken(userDetails: UserDetails, extraClaims: Map<String, Any>): String
}