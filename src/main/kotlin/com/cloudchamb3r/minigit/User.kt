package com.cloudchamb3r.minigit

import java.net.URL

data class User(
    val name: String,
    val email: String,
    val bio: String?,
    val profile: URL?,
) : GitRepoOwnable, GitRepoLikeable {
    override fun listRepositories(): List<GitRepo> {
        TODO("Not yet implemented")
    }
}