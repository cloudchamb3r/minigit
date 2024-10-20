package com.cloudchamb3r.minigit.service
import com.cloudchamb3r.minigit.extension.toAbsoluteURI
import org.eclipse.jgit.api.Git
import org.springframework.stereotype.Service
import java.io.File

@Service
class GitService {
    fun init(dir: File): Git= Git
        .init()
        .setDirectory(dir)
        .setBare(true)
        .setInitialBranch("main")
        .call()
}