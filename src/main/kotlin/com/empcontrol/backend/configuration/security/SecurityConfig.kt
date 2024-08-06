package com.empcontrol.backend.configuration.security

import com.empcontrol.backend.exception.UserNotFoundException
import com.empcontrol.backend.repository.CustomerRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    val customerRepository: CustomerRepository
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .cors { it.disable() }
            .sessionManagement { SessionCreationPolicy.STATELESS }
            .authenticationProvider(authenticationProvider())
            .authorizeHttpRequests { it.requestMatchers(HttpMethod.POST,"/auth/**").permitAll() }
            .authorizeHttpRequests { it.requestMatchers("/employees/**").permitAll() }
            .authorizeHttpRequests { it.anyRequest().permitAll() }

        return http.build()
    }

    @Bean
    fun authenticationProvider(): AuthenticationProvider{
        val authenticationStrategy: DaoAuthenticationProvider = DaoAuthenticationProvider()
        authenticationStrategy.setPasswordEncoder(passwordEncoder())
        authenticationStrategy.setUserDetailsService(userDetailsService())

        return authenticationStrategy
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder{
        return BCryptPasswordEncoder()
    }


    @Bean
    fun userDetailsService(): UserDetailsService {
        return UserDetailsService { username ->
            customerRepository.findByUsername(username).orElseThrow {
                UserNotFoundException("User $username not found.")
            }
        }
    }

    @Bean
    fun authenticationManager(
        authenticationConfiguration: AuthenticationConfiguration
    ): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }
}