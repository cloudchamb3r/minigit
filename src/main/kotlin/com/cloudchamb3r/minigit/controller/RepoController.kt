package com.cloudchamb3r.minigit.controller

import com.cloudchamb3r.minigit.common.annotation.ToLower
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
    fun getRepo(@ToLower @PathVariable owner: String, @ToLower @PathVariable repo: String): ResponseEntity<RepoVO> {
        log.info("get repo: {} {}", owner, repo)
        return ResponseEntity.ok(repoService.getRepo(owner, repo))
    }

    @GetMapping()
    fun listRepos() : ResponseEntity<List<RepoVO>> {
        return ResponseEntity.ok(repoService.listRepos())
    }

    @GetMapping("/{owner}")
    fun listOwnerRepos(@ToLower @PathVariable owner: String): ResponseEntity<List<RepoVO>> {
        return ResponseEntity.ok(repoService.listOwnerRepos(owner))
    }

    @PostMapping("/{owner}/{repo}")
    fun createRepo(@ToLower @PathVariable owner: String, @ToLower @PathVariable repo: String): ResponseEntity<Boolean> {
        return ResponseEntity.ok(repoService.createRepo(owner, repo))
    }

    @DeleteMapping("/{owner}/{repo}")
    fun deleteRepo(@ToLower @PathVariable owner: String, @ToLower @PathVariable repo: String) {
        repoService.deleteRepo(owner, repo)
    }
}