package com.cloudchamb3r.minigit

data class GitPull(
    val headCommit: GitCommit
): GitReferenceable {
    override fun getHeadCommit(): GitCommit {
        return headCommit
    }
}