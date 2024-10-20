package com.cloudchamb3r.minigit.service

import com.cloudchamb3r.minigit.config.prop.MiniGitConfigProps
import com.cloudchamb3r.minigit.repository.entity.RepoEntity
import com.cloudchamb3r.minigit.repository.entity.RepoRepository
import com.cloudchamb3r.minigit.service.vo.RepoVO
import jakarta.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.interceptor.TransactionAspectSupport

@Service
class RepoService(
    private val repoRepository: RepoRepository,
    private val gitService: GitService,
    private val miniGitConfig: MiniGitConfigProps,
) {
    private val log : Logger = LoggerFactory.getLogger(RepoService::class.java)

    fun repoExists(owner: String, repo: String): Boolean {
        return repoRepository.findByOwnerAndRepo(owner, repo) != null
    }

    @Transactional
    fun createRepo(owner: String, repo: String): Boolean {
        try {
            if (repoExists(owner, repo)) return false
            val entity = RepoEntity(0L, owner, repo)
            repoRepository.save(entity)
            gitService.init(owner, repo)
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
        return repoRepository.findAllByOwner(owner).map { it.convert() }
    }

    fun getRepo(owner: String, repo: String): RepoVO? {
        return repoRepository.findByOwnerAndRepo(owner, repo)?.convert()
    }

    fun deleteRepo(owner: String, repo: String): Boolean {
        return repoRepository.findByOwnerAndRepo(owner, repo).let { runCatching { repoRepository.delete(it!!) }.isSuccess }
    }
}