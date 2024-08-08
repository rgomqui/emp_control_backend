package com.empcontrol.backend.configuration.extra

import com.empcontrol.backend.services.UserService
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.actuate.info.Info
import org.springframework.boot.actuate.info.InfoContributor
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.PageRequest

@Configuration
class InfoCustom(
    @Value("\${app.author}")
    private val APP_AUTHOR: String,

    @Value("\${app.description}")
    private val APP_DESCRIPTION: String,

    @Value("\${app.name}")
    private val APP_NAME: String,

    @Value("\${app.version}")
    private val APP_VERSION: String,

    private val userService: UserService
): InfoContributor {
    override fun contribute(builder: Info.Builder?) {
        val usersRegistered =userService.findAll(PageRequest.of(0, 1)).totalElements

        builder?.withDetails( mapOf(
            "app" to mapOf(
                "name" to APP_NAME,
                "description" to APP_DESCRIPTION,
                "version" to APP_VERSION,
                "author" to APP_AUTHOR,
                "users_registered" to usersRegistered,
            )
        ))
    }
}