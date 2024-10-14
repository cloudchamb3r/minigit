package com.cloudchamb3r.minigit

data class GitRepo(
    val name: String,
    val description: String,
    val branches: List<GitBranch>,
    val tags: List<GitTag>,
    val visibility: GitRepoVisibility,

    val staredUsers: List<User>,
    val forkedRepositories: List<GitRepo>,
) {
    val starCount
        get() = staredUsers.size

    val forkCount: Int
        get() = forkedRepositories.size
}