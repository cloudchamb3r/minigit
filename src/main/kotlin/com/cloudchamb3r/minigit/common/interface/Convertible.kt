package com.cloudchamb3r.minigit.common.`interface`

import com.cloudchamb3r.minigit.service.vo.RepoVO

interface Convertible<To> {
    fun convert(): RepoVO
}