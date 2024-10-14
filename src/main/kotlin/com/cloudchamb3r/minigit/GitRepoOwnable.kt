package com.cloudchamb3r.minigit

interface GitRepoOwnable {
    // list all repository of owner
    fun listRepositories(): List<GitRepo>
    // list repository of owner that matches name
    fun getRepository(name: String): GitRepo?
}