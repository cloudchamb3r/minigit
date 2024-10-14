package com.cloudchamb3r.minigit

import java.net.URL

data class User(
    val name: String,
    val email: String,
    val bio: String?,
    val profile: URL?,
    private val repositories: List<GitRepo> = listOf()
) : GitRepoOwnable, GitRepoLikeable {
    override fun listRepositories(): List<GitRepo> {
        return repositories
    }

    override fun getRepository(name: String): GitRepo? {
        return repositories.firstOrNull { it.name == name }
    }
}