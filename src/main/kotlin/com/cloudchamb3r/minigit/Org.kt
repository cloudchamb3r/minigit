package com.cloudchamb3r.minigit

data class Org(
    val name: String,
    private val repositories: List<GitRepo> = listOf()
) : GitRepoOwnable {
    override fun listRepositories(): List<GitRepo> {
        return repositories
    }

    override fun getRepository(name: String): GitRepo? {
        return repositories.firstOrNull { it.name == name }
    }
}