package com.cloudchamb3r.minigit.service
import com.cloudchamb3r.minigit.git.client.GitClient
import com.cloudchamb3r.minigit.git.client.GitClients
import com.cloudchamb3r.minigit.git.transfer.GitTransfer
import com.cloudchamb3r.minigit.git.transfer.GitTransfers
import com.cloudchamb3r.minigit.service.vo.GitAuthorVO
import com.cloudchamb3r.minigit.service.vo.GitFileVO
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class GitService(
    private val gitClients: GitClients,
    private val gitTransfers: GitTransfers,
) {
    companion object{
        private val log = LoggerFactory.getLogger(GitService::class.java)
    }

    fun init(owner: String, repo: String): GitClient {
        return gitClients.createClient(owner, repo)
    }

    fun commitFiles(
        owner: String,
        repo: String,
        files: List<GitFileVO>,
        commitMessage: String,
        author: GitAuthorVO = GitAuthorVO("minigit", "minigit@git.com"),
        branch: String = "main",
    ): Boolean {
        val gitClient = gitClients.getClient(owner, repo)
        return runCatching { gitClient!!.commit(files, commitMessage, author) }.isSuccess
    }

    fun transfer(owner: String, repo: String): GitTransfer {
        return gitTransfers.getTransfer(owner, repo)
    }
}