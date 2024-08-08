package com.empcontrol.backend.configuration.security.authorization

import com.empcontrol.backend.domain.Operation
import com.empcontrol.backend.exception.ObjectNotFoundException
import com.empcontrol.backend.repository.OperationRepository
import com.empcontrol.backend.repository.UserRepository
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.core.Authentication
import org.springframework.security.web.access.intercept.RequestAuthorizationContext
import org.springframework.stereotype.Component
import java.util.function.Supplier
import java.util.regex.Pattern

@Component
class CustomAuthorizationManager(
    private val operationRepository: OperationRepository,
    private val userService: UserRepository
): AuthorizationManager<RequestAuthorizationContext> {
    override fun check(
        authentication: Supplier<Authentication>?,
        requestContext: RequestAuthorizationContext?
    ): AuthorizationDecision? {
        val request = requestContext?.request
        if(!request?.requestURI.isNullOrBlank()){
            val uri = extractURL(request!!)

            val isPublic:Boolean = isPublic(uri, request.method)

            if(isPublic) return AuthorizationDecision(true)
            
            val isGranted = isGranted(uri, request.method, authentication?.get())

            return AuthorizationDecision(isGranted)
        }
        return AuthorizationDecision(false)

    }

    private fun isGranted(uri: String, httpMethod: String?, authentication: Authentication?): Boolean {
        if (authentication === null || authentication !is UsernamePasswordAuthenticationToken){
            throw AuthenticationCredentialsNotFoundException("User not logged in.")
        }

        val operations = obtainOperations(authentication)

        return containOperations(operations, uri, httpMethod)
    }

    private fun obtainOperations(authentication: Authentication): List<Operation> {
        val authToken =  authentication as UsernamePasswordAuthenticationToken
        val username: String = authToken.principal.toString()

        val user = userService.findByUsername(username).orElseThrow { ObjectNotFoundException("User $username not found.") }

        return user.role.permisions.asSequence().map { it.operation }.toCollection(mutableListOf())
    }

    private fun isPublic(uri: String, httpMethod: String): Boolean {
        val publicAccessOperations: List<Operation> = operationRepository.findByPublicAccess();

         return containOperations(publicAccessOperations, uri, httpMethod)
    }

    private fun containOperations(
        operations: List<Operation>,
        uri: String,
        httpMethod: String?
    ): Boolean {
        return operations.any {
            val pattern = Pattern.compile("${it.module.basePath}${it.path}")
            pattern.matcher(uri).matches() && it.httpMethod == httpMethod

        }
    }

    private fun extractURL(request: HttpServletRequest): String {
        val contextPath = request.contextPath
        val uri = request.requestURI.replace(contextPath, "")
        return uri
    }
}