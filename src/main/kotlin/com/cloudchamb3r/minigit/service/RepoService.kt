package com.cloudchamb3r.minigit.service

import com.cloudchamb3r.minigit.config.MiniGitConfig
import com.cloudchamb3r.minigit.repository.entity.RepoEntity
import com.cloudchamb3r.minigit.repository.entity.RepoRepository
import com.cloudchamb3r.minigit.service.vo.RepoVO
import jakarta.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.interceptor.TransactionAspectSupport
import java.sql.Connection

@Service
class RepoService(
    private val repoRepository: RepoRepository,
    private val gitService: GitService,
    private val miniGitConfig: MiniGitConfig,
) {
    private val log : Logger = LoggerFactory.getLogger(RepoService::class.java)

    fun repoExists(owner: String, repo: String): Boolean {
        val owner = owner.lowercase()
        val repo = repo.lowercase()
        return repoRepository.findByOwnerAndRepo(owner, repo) != null
    }

    @Transactional
    fun createRepo(owner: String, repo: String): Boolean {
        val owner = owner.lowercase()
        val repo = repo.lowercase()
        try {
            if (repoExists(owner, repo)) return false
            val entity = RepoEntity(0L, owner, repo)
            repoRepository.save(entity)
            val gitDir = miniGitConfig.getGitDir(owner, repo)
            gitService.init(gitDir)
        }
        catch (e: Exception) {
            log.error("Error on createRepo {}/{}", owner, repo)
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly()
            return false
        }
        return true
    }

    fun listRepos(): List<RepoVO> {
        return repoRepository.findAll().map { it.convert() }
    }

    fun listOwnerRepos(owner: String): List<RepoVO> {
        val owner = owner.lowercase()
        return repoRepository.findAllByOwner(owner).map { it.convert() }
    }

    fun getRepo(owner: String, repo: String): RepoVO? {
        val owner = owner.lowercase()
        val repo = repo.lowercase()
        return repoRepository.findByOwnerAndRepo(owner, repo)?.convert()
    }

    fun deleteRepo(owner: String, repo: String): Boolean {
        val owner = owner.lowercase()
        val repo = repo.lowercase()
        return repoRepository.findByOwnerAndRepo(owner, repo).let { runCatching { repoRepository.delete(it!!) }.isSuccess }
    }
}