package com.cloudchamb3r.minigit

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class RepoController(
    val userRepository: UserRepository,
    private val orgRepository: OrgRepository
) {
    @GetMapping("/{owner}")
    fun listOwnerRepos(@PathVariable("owner") ownerName: String): List<GitRepo> {
        val entity = userRepository.findByName(ownerName)
            ?: orgRepository.findByName(ownerName)
        if (entity == null) return listOf()
        val owner = entity.toDto()
        return owner.listRepositories()
    }

    @GetMapping("/{owner}/{repo}")
    fun getOwnerRepo(@PathVariable("owner") ownerName: String, @PathVariable("repo") repoName: String) : GitRepo? {
        val entity = userRepository.findByName(ownerName)
            ?: orgRepository.findByName(ownerName)
        if (entity == null) return null
        val owner = entity.toDto()
        return owner.getRepository(repoName)
    }

}