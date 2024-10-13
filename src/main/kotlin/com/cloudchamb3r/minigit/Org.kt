package com.cloudchamb3r.minigit

data class Org(
    val name: String,
) : GitRepoOwnable {
    override fun listRepositories(): List<GitRepo> {
        TODO("Not yet implemented")
    }
}