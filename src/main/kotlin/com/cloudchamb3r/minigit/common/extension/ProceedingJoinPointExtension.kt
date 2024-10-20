package com.cloudchamb3r.minigit.common.extension

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.reflect.MethodSignature
import java.lang.reflect.Method

fun ProceedingJoinPoint.currentMethod(): Method? {
    if (signature !is MethodSignature) return null
    val sig = signature as MethodSignature
    return target.javaClass.getMethod(sig.name, *sig.parameterTypes)
}