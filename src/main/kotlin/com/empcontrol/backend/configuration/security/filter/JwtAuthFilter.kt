package com.empcontrol.backend.configuration.security.filter

import com.empcontrol.backend.exception.ObjectNotFoundException
import com.empcontrol.backend.repository.UserRepository
import com.empcontrol.backend.services.auth.JWTService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    private val jwtService: JWTService,
    private val userRepository: UserRepository
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,

    ) {
        val authorizationHeader = request.getHeader("Authorization")
        if(authorizationHeader.isNullOrBlank() || !authorizationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response)
            return
        }

        val jwt = authorizationHeader.split(" ")[1]

        val username = jwtService.extractUsername(jwt)

        val user = userRepository.findByUsername(username).orElseThrow{ ObjectNotFoundException("Employee $username not found.") }

        val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(username, null, user.authorities)

        SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken

        filterChain.doFilter(request, response)
    }
}