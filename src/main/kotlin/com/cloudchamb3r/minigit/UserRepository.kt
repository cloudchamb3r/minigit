package com.cloudchamb3r.minigit

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<Long, UserEntity>{
    fun findByName(name: String) : UserEntity?
}