package com.cloudchamb3r.minigit

data class GitRepo(
    val name: String,
    val description: String,
    val branches: List<GitBranch>,
    val tags: List<GitTag>,
    val visibility: GitRepoVisibility,
    val starCount: Int,
)