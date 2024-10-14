package com.cloudchamb3r.minigit

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "repo")
class GitRepoEntity(
    @field:Id
    @field:GeneratedValue
    val id: Long,
    @field:Column
    val name: String,
    @field:Column
    val description: String,

) {
}