package com.cloudchamb3r.minigit.controller

import com.cloudchamb3r.minigit.repository.entity.RepoRepository
import com.cloudchamb3r.minigit.service.GitService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import javax.print.attribute.standard.Media
// refs. https://git-scm.com/docs/http-protocol/2.34.0
@RestController
class GitController(
    private val repoRepository: RepoRepository,
    private val gitService: GitService,
) {
    companion object{
        private val logger = LoggerFactory.getLogger(GitController::class.java)
    }

    // TODO: Return type should be changed
    @GetMapping("/{owner}/{repo}/info/refs")
    fun getRefs(@PathVariable owner: String, @PathVariable repo: String, service: String?): ResponseEntity<String> {
        return gitService.transfer(owner, repo).let {
            when (service) {
                "git-upload-pack" -> {
                    val infoUploadPackHeader = HttpHeaders().apply {
                        contentType = MediaType("application/x-git-upload-pack-advertisement")
                    }
                    ResponseEntity(it.infoUploadPack(), infoUploadPackHeader, HttpStatus.OK)
                }
                "git-receive-pack" -> {
                    val infoReceivePackHeader = HttpHeaders().apply {
                        contentType = MediaType("application/x-git-receive-pack-advertisement")
                    }
                    ResponseEntity(it.infoReceivePack(), infoReceivePackHeader, HttpStatus.OK)
                }
                else -> ResponseEntity(it.infoRefs(), HttpStatus.OK) // dumb protocol
            }
        }
    }

    @GetMapping("/{owner}/{repo}/HEAD")
    fun getHead(@PathVariable owner: String, @PathVariable repo: String): ResponseEntity<String> {
        return ResponseEntity(gitService.transfer(owner, repo).head(), HttpStatus.OK)
    }

    @PostMapping("/{owner}/{repo}/git-receive-pack")
    fun receivePack(@PathVariable owner: String, @PathVariable repo: String): ResponseEntity<String> {
        val transfer = gitService.transfer(owner, repo)
        return ResponseEntity(transfer.handleReceivePack(), HttpStatus.OK)
    }

    @PostMapping("/{owner}/{repo}/git-upload-pack")
    fun uploadPack(@PathVariable owner: String, @PathVariable repo: String): ResponseEntity<String> {
        val transfer = gitService.transfer(owner, repo)
        return ResponseEntity(transfer.handleUploadPack(), HttpStatus.OK)
    }
}