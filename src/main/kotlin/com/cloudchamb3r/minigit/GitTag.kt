package com.cloudchamb3r.minigit

data class GitTag(
    val name: String,
) : GitReferenceable {
    override fun getHeadCommit(): GitCommit {
        TODO("Not yet implemented")
    }
}