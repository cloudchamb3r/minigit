package com.cloudchamb3r.minigit

import jakarta.persistence.Embeddable
import java.io.Serializable
import java.util.*

@Embeddable
class GitRepoUserId(
    private val userId: Long,
    private val repoId: Long,
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as GitRepoUserId
        return this.userId == that.userId &&
                this.repoId == that.repoId
    }

    override fun hashCode(): Int {
        return Objects.hash(userId, repoId)
    }
}