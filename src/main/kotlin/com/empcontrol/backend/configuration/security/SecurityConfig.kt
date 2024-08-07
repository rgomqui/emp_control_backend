package com.empcontrol.backend.configuration.security

import com.empcontrol.backend.configuration.filter.JwtAuthFilter
import com.empcontrol.backend.enums.RolePermissions
import com.empcontrol.backend.exception.ObjectNotFoundException
import com.empcontrol.backend.repository.EmployeeRepository
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val employeeRepository: EmployeeRepository,
    private val jwtAuthFilter: JwtAuthFilter
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .cors { it.disable() }
            .sessionManagement { SessionCreationPolicy.STATELESS }
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
            .authorizeHttpRequests { it.requestMatchers(HttpMethod.POST,"/auth/**").permitAll() }
            .authorizeHttpRequests { it.requestMatchers(HttpMethod.GET, "/auth/**").permitAll() }
            .authorizeHttpRequests { it.requestMatchers(HttpMethod.GET, "/employees").hasAuthority(RolePermissions.READ_ALL_EMPLOYEES.name) }
            .authorizeHttpRequests { it.requestMatchers(HttpMethod.GET, "/employees/{employeeId}").hasAuthority(RolePermissions.READ_ONE_EMPLOYEE.name) }
            .authorizeHttpRequests { it.requestMatchers(HttpMethod.PUT, "/employees/{employeeId}").hasAuthority(RolePermissions.UPDATE_ONE_EMPLOYEE.name) }
            .authorizeHttpRequests { it.requestMatchers(HttpMethod.PUT, "/employees/{employeeId}/disable").hasAuthority(RolePermissions.DISABLE_ONE_EMPLOYEE.name) }
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
            employeeRepository.findByUsername(username).orElseThrow {
                ObjectNotFoundException("User $username not found.")
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