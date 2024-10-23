package com.cloudchamb3r.minigit.config

import com.cloudchamb3r.minigit.git.client.GitClientSupplier
import com.cloudchamb3r.minigit.git.client.JGitClient
import com.cloudchamb3r.minigit.git.transfer.GitTransfer
import com.cloudchamb3r.minigit.git.transfer.GitTransferSupplier
import com.cloudchamb3r.minigit.git.transfer.JGitTransfer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GitConfig {
    @Bean
    fun gitClientSupplier(): GitClientSupplier =  GitClientSupplier { JGitClient() }

    @Bean
    fun gitTransferSupplier(): GitTransferSupplier = GitTransferSupplier { JGitTransfer() }
}