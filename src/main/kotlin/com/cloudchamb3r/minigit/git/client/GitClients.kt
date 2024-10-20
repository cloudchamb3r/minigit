package com.cloudchamb3r.minigit.git.client

import com.cloudchamb3r.minigit.config.prop.MiniGitConfigProps
import org.springframework.stereotype.Component

@Component
class GitClients(
    private val miniGitConfig: MiniGitConfigProps,
    private val gitClientSupplier: GitClientSupplier,
) {
    fun getClient(owner: String, repo: String): GitClient? {
        val gitDir = miniGitConfig.getGitDir(owner, repo)
        return gitClientSupplier.get().run {
            if (gitDir.exists()) {
                from(gitDir)
                return@run this
            }
            null
        }
    }

    fun createClient(owner: String, repo: String): GitClient {
        val gitDir = miniGitConfig.getGitDir(owner, repo)
        return gitClientSupplier.get().run {
            if (gitDir.exists()) from(gitDir) else new(gitDir)
        }
    }

}