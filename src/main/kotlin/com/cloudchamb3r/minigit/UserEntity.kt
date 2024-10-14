package com.cloudchamb3r.minigit

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.net.URL

@Entity
@Table(name = "usr")
class UserEntity(
    @field:Id
    @field:GeneratedValue
    val id: Long,
    @field:Column
    val name: String,
    @field:Column
    val email: String,
    @field:Column
    val bio: String?,
    @field:Column
    val profile: String?,
) : DtoCompatible<User> {
    override fun toDto(): User {
        val profileUrl = URL(this.profile)
        return User(this.name, this.email, this.bio, profileUrl)
    }
}