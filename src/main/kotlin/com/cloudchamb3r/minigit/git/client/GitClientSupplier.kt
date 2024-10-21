package com.cloudchamb3r.minigit.git.client

@FunctionalInterface
fun interface GitClientSupplier {
    fun get(): GitClient
}