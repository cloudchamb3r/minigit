package com.cloudchamb3r.minigit

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MiniGitApplication

fun main(args: Array<String>) {
    runApplication<MiniGitApplication>(*args)
}
