package com.cloudchamb3r.minigit.git.client

@FunctionalInterface
interface GitClientSupplier {
    fun get(): GitClient
}