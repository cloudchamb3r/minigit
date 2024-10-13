package com.cloudchamb3r.minigit

data class GitBranch(
    val name: String,
) : GitReferenceable {
    override fun getHeadCommit(): GitCommit {
        TODO("Not yet implemented")
    }
}