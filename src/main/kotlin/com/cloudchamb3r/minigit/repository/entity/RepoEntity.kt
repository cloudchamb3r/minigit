package com.cloudchamb3r.minigit.repository.entity

import com.cloudchamb3r.minigit.common.`interface`.Convertible
import com.cloudchamb3r.minigit.service.vo.RepoVO
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "repo")
class RepoEntity(
    @field:Id
    @field:GeneratedValue
    val id: Long,
    @field:Column
    val owner: String,
    @field:Column
    val repo: String,
) : Convertible<RepoVO> {
    override fun convert(): RepoVO = RepoVO(owner, repo)
}