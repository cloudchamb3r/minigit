package com.cloudchamb3r.minigit.config

import com.cloudchamb3r.minigit.git.client.GitClient
import com.cloudchamb3r.minigit.git.client.GitClientSupplier
import com.cloudchamb3r.minigit.git.client.JGitClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GitClientConfig {
    @Bean
    fun gitClientSupplier(): GitClientSupplier {
        return object :GitClientSupplier {
            override fun get(): GitClient {
                return JGitClient()
            }
        }
    }
}