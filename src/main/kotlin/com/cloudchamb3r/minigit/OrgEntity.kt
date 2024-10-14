package com.cloudchamb3r.minigit

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "org")
class OrgEntity(
    @field:Id
    @field:GeneratedValue
    val id: Long,
    @field:Column
    val name: String,
) : DtoCompatible<Org> {
    override fun toDto(): Org {
        return Org(name)
    }
}