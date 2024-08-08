package com.empcontrol.backend.configuration.security

import com.empcontrol.backend.configuration.security.authorization.CustomAuthorizationManager
import com.empcontrol.backend.configuration.security.filter.JwtAuthFilter
import com.empcontrol.backend.configuration.security.handler.CustomAccessDeniedHandler
import com.empcontrol.backend.configuration.security.handler.CustomAuthenticationEntrypoint
import com.empcontrol.backend.enums.UserRolesEnum
import com.empcontrol.backend.enums.RolePermissionsEnum
import com.empcontrol.backend.exception.ObjectNotFoundException
import com.empcontrol.backend.repository.UserRepository
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
//@EnableMethodSecurity(
//    prePostEnabled = true
//)
class SecurityConfig(
    private val userRepository: UserRepository,
    private val jwtAuthFilter: JwtAuthFilter,
    private val customAuthenticationEntrypoint: CustomAuthenticationEntrypoint,
    private val customAccessDeniedHandler: CustomAccessDeniedHandler,
    private val customAuthorizationManager: CustomAuthorizationManager
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .cors { it.disable() }
            .sessionManagement { SessionCreationPolicy.STATELESS }
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling{ it.authenticationEntryPoint(customAuthenticationEntrypoint)}
            .exceptionHandling{ it.accessDeniedHandler(customAccessDeniedHandler)}
//            .authorizeHttpRequests { it.requestMatchers("/auth/**").permitAll() }
//            .authorizeHttpRequests { it.requestMatchers("/auth/profile").authenticated() }
//            [*] Authorization role and permissions based [*]
//            .authorizeHttpRequests { it.requestMatchers(HttpMethod.GET, "/employees").hasAnyRole(UserRolesEnum.ADMIN.name) }
//            .authorizeHttpRequests { it.requestMatchers(HttpMethod.GET, "/employees/{employeeId}").hasAnyRole(UserRolesEnum.ADMIN.name, UserRolesEnum.USER.name) }
//            .authorizeHttpRequests { it.requestMatchers(HttpMethod.PUT, "/employees/{employeeId}").hasAuthority(RolePermissionsEnum.UPDATE_ONE_EMPLOYEE.name) }
//            .authorizeHttpRequests { it.requestMatchers(HttpMethod.PUT, "/employees/{employeeId}/disable").hasAuthority(RolePermissionsEnum.DISABLE_ONE_EMPLOYEE.name) }
//            [*] Authorization role and permissions based [*]
            .authorizeHttpRequests { it.anyRequest().access(customAuthorizationManager) }

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
            userRepository.findByUsername(username).orElseThrow {
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