package com.cloudchamb3r.minigit.common.aspect

import com.cloudchamb3r.minigit.common.annotation.ToLower
import com.cloudchamb3r.minigit.common.aspect.PointCut.Companion.POINTCUT
import com.cloudchamb3r.minigit.common.extension.currentMethod
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class ToLowerAspect {
    @Around("$POINTCUT.hasStringParameter() && $POINTCUT.annotatedWithToLower()")
    fun toLower(pjp: ProceedingJoinPoint): Any {
        val method = pjp.currentMethod()?: return pjp.proceed(pjp.args)
        val annotations = method.parameterAnnotations
        val newArgs = pjp.args.mapIndexed { i, arg ->
            if (arg !is String) return@mapIndexed arg
            val strArg = arg as String
            val annotated = annotations[i].find { it is ToLower } != null
            return@mapIndexed if (annotated) strArg.lowercase() else strArg
        }.toTypedArray()
        return pjp.proceed(newArgs)
    }
}