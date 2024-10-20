package com.cloudchamb3r.minigit.config.prop

import org.springframework.boot.context.properties.ConfigurationProperties
import java.io.File
import java.nio.file.InvalidPathException
import java.nio.file.Path

@ConfigurationProperties(prefix = "minigit")
class MiniGitConfigProps(
    private val data: String = "./data",
) {
    fun getGitDir(owner: String, repo: String): File = Path.of(data, owner, repo).toFile().let {
        val owner = owner.lowercase()
        val repo = repo.lowercase()
        if (it.isFile) throw InvalidPathException("@data/$owner/$repo", "Not a valid directory")
        return it
    }
}