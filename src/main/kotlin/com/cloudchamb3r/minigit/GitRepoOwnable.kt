package com.cloudchamb3r.minigit

interface GitRepoOwnable {
    fun listRepositories() : List<GitRepo>
}