package com.empcontrol.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [HibernateJpaAutoConfiguration::class, ])
class BackendApplication

fun main(args: Array<String>) {
	runApplication<BackendApplication>(*args)
}
