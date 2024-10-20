package com.cloudchamb3r.minigit.git.client

import com.cloudchamb3r.minigit.service.vo.GitAuthorVO
import com.cloudchamb3r.minigit.service.vo.GitFileVO
import java.io.File

interface GitClient {
    fun new(dir: File): GitClient
    fun from(dir: File): GitClient
    fun checkout(branch: String)
    fun commit(files: List<GitFileVO>, message: String, author: GitAuthorVO)
}