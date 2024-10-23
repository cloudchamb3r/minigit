package com.cloudchamb3r.minigit.git.transfer

import com.cloudchamb3r.minigit.config.prop.MiniGitConfigProps
import org.springframework.stereotype.Component

@Component
class GitTransfers(
    private val miniGitConfig: MiniGitConfigProps,
    private val gitTransferSupplier: GitTransferSupplier,
) {
    fun getTransfer(owner: String, repo: String): GitTransfer {
        val dir = miniGitConfig.getGitDir(owner, repo)
        return gitTransferSupplier.get().apply {
            on(dir)
        }
    }
}