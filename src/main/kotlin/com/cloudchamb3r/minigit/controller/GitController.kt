package com.cloudchamb3r.minigit.controller

import com.cloudchamb3r.minigit.repository.entity.RepoRepository
import com.cloudchamb3r.minigit.service.GitService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

// refs. https://git-scm.com/docs/http-protocol/2.34.0
@RestController
class GitController(
    private val repoRepository: RepoRepository,
    private val gitService: GitService,
) {
    companion object {
        private val logger = LoggerFactory.getLogger(GitController::class.java)
    }

    @GetMapping("/{owner}/{repo}/info/refs")
    fun getRefs(@PathVariable owner: String, @PathVariable repo: String, service: String?): Mono<ResponseEntity<Sequence<ByteArray>>> {
        val transfer = gitService.transfer(owner, repo)

        val responseSequence = when (service) {
            "git-upload-pack" -> transfer.infoUploadPack()
            "git-receive-pack" -> transfer.infoReceivePack()
            else -> transfer.infoRefs()
        }

        val responseHeader = when (service) {
            "git-upload-pack" -> HttpHeaders().apply {
                contentType = MediaType("application", "x-git-upload-pack-advertisement")
                cacheControl = "no-cache"
            }
            "git-receive-pack" -> HttpHeaders().apply {
                contentType = MediaType("application", "x-git-receive-pack-advertisement")
                cacheControl = "no-cache"
            }
            else -> HttpHeaders().apply { contentType = MediaType.TEXT_PLAIN }
        }

        return Mono.create {
            it.success(
                ResponseEntity(responseSequence, responseHeader, HttpStatus.OK)
            )
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
    // for test only
//    @GetMapping("/flow-test")
//    fun flowTest(): Mono<ResponseEntity<Sequence<String>>> {
//        val header = HttpHeaders().apply {
//            contentType = MediaType("application", "x-flow-test")
//        }
//        return Mono.create {
//            it.success(
//                ResponseEntity.ok()
//                    .headers(header)
//                    .body(
//                        sequence {
//                            yield("a1")
//                            yield("b2")
//                        }
//                    )
//            )
//        }
//    }
}