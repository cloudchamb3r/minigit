package com.cloudchamb3r.minigit

import java.time.LocalDateTime

data class GitCommit(
    val commitId: String,
    val author: GitAuthor,
    val commitDate: LocalDateTime,
    val message: String,
)