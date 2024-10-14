package com.cloudchamb3r.minigit

data class GitBranch(
    val name: String,
) : GitReferenceable {
    override fun getHead(): GitCommit {
        TODO("Not yet implemented")
    }
}