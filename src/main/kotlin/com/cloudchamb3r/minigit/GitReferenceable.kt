package com.cloudchamb3r.minigit

interface GitReferenceable {
    fun getHead(): GitCommit
}