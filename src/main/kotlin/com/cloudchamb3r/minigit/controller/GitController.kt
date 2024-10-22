package com.cloudchamb3r.minigit.controller

import com.cloudchamb3r.minigit.repository.entity.RepoRepository
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GitController(
    private val repoRepository: RepoRepository
) {
    companion object{
        private val logger = LoggerFactory.getLogger(GitController::class.java)
    }

    @GetMapping("/{owner}/{repo}/info/refs")
    fun getRefs(@PathVariable owner: String, @PathVariable repo: String, service: String?): ResponseEntity<String> {
        repoRepository.findByOwnerAndRepo(owner, repo) ?: return ResponseEntity.notFound().build()
        if (service == null) {
            return ResponseEntity.ok("dumb protocol")
        }
        return ResponseEntity.ok("smart protocol")
    }
}