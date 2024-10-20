package com.cloudchamb3r.minigit.repository.entity

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RepoRepository : JpaRepository<RepoEntity, Long> {
    fun findByOwnerAndRepo(owner: String, repo: String) : RepoEntity?
    fun findAllByOwner(owner: String): List<RepoEntity>
}