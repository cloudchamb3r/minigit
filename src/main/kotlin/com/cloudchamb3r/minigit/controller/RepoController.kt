package com.cloudchamb3r.minigit.controller

import com.cloudchamb3r.minigit.service.RepoService
import com.cloudchamb3r.minigit.service.vo.RepoVO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class RepoController(
    val repoService: RepoService
) {
    val log: Logger = LoggerFactory.getLogger(RepoController::class.java)

    @GetMapping("/{owner}/{repo}")
    fun getRepo(@PathVariable owner: String, @PathVariable repo: String): ResponseEntity<RepoVO> {
        return ResponseEntity.ok(repoService.getRepo(owner, repo))
    }

    @GetMapping()
    fun listRepos() : ResponseEntity<List<RepoVO>> {
        return ResponseEntity.ok(repoService.listRepos())
    }

    @GetMapping("/{owner}")
    fun listOwnerRepos(@PathVariable owner: String): ResponseEntity<List<RepoVO>> {
        return ResponseEntity.ok(repoService.listOwnerRepos(owner))
    }

    @PostMapping("/{owner}/{repo}")
    fun createRepo(@PathVariable owner: String, @PathVariable repo: String): ResponseEntity<Boolean> {
        return ResponseEntity.ok(repoService.createRepo(owner, repo))
    }

    @DeleteMapping("/{owner}/{repo}")
    fun deleteRepo(@PathVariable owner: String, @PathVariable repo: String) {
        repoService.deleteRepo(owner, repo)
    }
}