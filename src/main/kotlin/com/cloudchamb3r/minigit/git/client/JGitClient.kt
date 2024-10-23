package com.cloudchamb3r.minigit.git.client

import com.cloudchamb3r.minigit.service.vo.GitAuthorVO
import com.cloudchamb3r.minigit.service.vo.GitFileVO
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.dircache.DirCacheEntry
import org.eclipse.jgit.internal.storage.pack.PackOutputStream
import org.eclipse.jgit.lib.Constants
import org.eclipse.jgit.lib.FileMode
import org.eclipse.jgit.transport.PacketLineOut
import java.io.ByteArrayOutputStream
import java.io.File

class JGitClient: GitClient {
    private lateinit var git : Git

    override fun new(dir: File): GitClient {
        git = Git.init()
            .setDirectory(dir)
            .setBare(true)
            .setInitialBranch("main")
            .call()
        return this
    }

    override fun from(dir: File): GitClient {
        git = Git.open(dir)
        return this
    }

    override fun commit(files: List<GitFileVO>, message: String, author: GitAuthorVO) {
        val repo = git.repository
        val index = repo.lockDirCache()
        index.builder().let { builder ->
            val inserter = repo.newObjectInserter()
            files.forEach {
                val oid = inserter.insert(Constants.OBJ_BLOB, it.content.toByteArray())
                val dirCacheEntry = DirCacheEntry(it.name).apply {
                    setObjectId(oid) // I don't know why but objectId = oid doesn't working
                    setFileMode(FileMode.REGULAR_FILE) // for consistency
                }
                builder.add(dirCacheEntry)
            }
            builder.finish()
        }
        index.write()
        index.commit()
        git.commit().setMessage(message).setAuthor(author.name, author.email).call()
    }

    override fun checkout(branch: String) {
        val branchExists = git.repository.findRef(branch) != null
        git.checkout().setCreateBranch(!branchExists).setName(branch).call()
    }
}