package com.cloudchamb3r.minigit

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table
import java.io.Serializable

@Entity
@Table(name = "repo_usr")
class GitRepoUserEntity(
    @field:EmbeddedId
    val id: GitRepoUserId,
    @field:ManyToOne
    @field:MapsId("userId")
    @field:JoinColumn(name = "userId")
    val user: UserEntity,
    @field:ManyToOne
    @field:MapsId("repoId")
    @field:JoinColumn(name = "repoId")
    val repo: GitRepoEntity,
    @field:Enumerated(EnumType.STRING)
    val role: GitRepoUserRole,
): Serializable {

}