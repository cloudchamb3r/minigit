package com.cloudchamb3r.minigit

interface DtoCompatible<DTO> {
    fun toDto() : DTO
}