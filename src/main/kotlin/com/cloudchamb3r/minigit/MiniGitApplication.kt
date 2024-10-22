package com.cloudchamb3r.minigit

import com.cloudchamb3r.minigit.git.client.GitClientSupplier
import com.cloudchamb3r.minigit.git.client.JGitClient
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class MiniGitApplication

fun main(args: Array<String>) {
    runApplication<MiniGitApplication>(*args)
}
