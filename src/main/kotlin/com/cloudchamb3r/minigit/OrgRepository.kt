package com.cloudchamb3r.minigit

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrgRepository : JpaRepository<Long, OrgEntity>{
    fun findByName(name: String): OrgEntity?
}