package com.cloudchamb3r.minigit.common.aspect

import org.aspectj.lang.annotation.Pointcut


class PointCut {
    companion object{
        public const val POINTCUT = "com.cloudchamb3r.minigit.common.aspect.PointCut"
    }
    @Pointcut("execution(* *(..,String...))")
    fun hasStringParameter() {}

    @Pointcut("@annotation(com.cloudchamb3r.minigit.common.annotation.ToLower")
    fun annotatedWithToLower() {}
}