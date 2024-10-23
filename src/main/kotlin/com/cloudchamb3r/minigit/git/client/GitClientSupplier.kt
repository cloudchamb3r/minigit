package com.cloudchamb3r.minigit.git.client

fun interface GitClientSupplier {
    fun get(): GitClient
}