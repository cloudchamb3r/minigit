package com.cloudchamb3r.minigit.controller

import com.cloudchamb3r.minigit.repository.entity.RepoRepository
import com.cloudchamb3r.minigit.service.GitService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GitController(
    private val repoRepository: RepoRepository,
    private val gitService: GitService,
) {
    companion object{
        private val logger = LoggerFactory.getLogger(GitController::class.java)
    }

    @GetMapping("/{owner}/{repo}/info/refs")
    fun getRefs(@PathVariable owner: String, @PathVariable repo: String, service: String?): ResponseEntity<String> {
        return gitService.transfer(owner, repo).let {
            when (service) {
                "git-upload-pack" -> ResponseEntity(it.gitUploadPack(), HttpStatus.OK)
                "git-receive-pack" -> ResponseEntity(it.gitReceivePack(), HttpStatus.OK)
                else -> ResponseEntity(it.infoRefs(), HttpStatus.OK)
            }
        }
    }

    @GetMapping("/{owner}/{repo}/HEAD")
    fun getHead(@PathVariable owner: String, @PathVariable repo: String): ResponseEntity<String> {
        return ResponseEntity(gitService.transfer(owner, repo).head(), HttpStatus.OK)
    }

}