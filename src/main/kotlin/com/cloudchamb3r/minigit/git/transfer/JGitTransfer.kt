package com.cloudchamb3r.minigit.git.transfer

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.transport.PacketLineOut
import org.eclipse.jgit.transport.ReceivePack
import org.eclipse.jgit.transport.RefAdvertiser
import org.eclipse.jgit.transport.UploadPack
import org.slf4j.LoggerFactory
import java.io.ByteArrayOutputStream
import java.io.File

class JGitTransfer: GitTransfer {
    companion object {
        private val log = LoggerFactory.getLogger(JGitTransfer::class.java)
    }

    private lateinit var git : Git

    override fun on(dir: File): GitTransfer {
        git = Git.open(dir)
        return this
    }

    override fun infoRefs(): ByteArray {
        return git.repository.refDatabase.refs.joinToString { "\n" }.toByteArray()
    }

    override fun head(): String {
        TODO("Not yet implemented")
    }

    override fun objects(commit: String): ByteArray {
        TODO("Not yet implemented")
    }

    override fun objectsInfoAlternatives(): String {
        TODO("Not yet implemented")
    }

    override fun objectsInfoPacks(): String {
        TODO("Not yet implemented")
    }

    override fun objectsInfoPack(packId: String): ByteArray {
        TODO("Not yet implemented")
    }

    override fun infoReceivePack(): ByteArray {
        val byteOutputStream = ByteArrayOutputStream()

        // write info receive pack response
        byteOutputStream.write("001f# service=git-receive-pack\n".toByteArray())
        byteOutputStream.write("0000".toByteArray())

        val receivePack = ReceivePack(git.repository)
        val advertiser = RefAdvertiser.PacketLineOutRefAdvertiser(PacketLineOut(byteOutputStream))
        receivePack.sendAdvertisedRefs(advertiser)

        byteOutputStream.write("0000".toByteArray())
        return byteOutputStream.toByteArray()
    }

    override fun infoUploadPack(): ByteArray {
        val byteOutputStream = ByteArrayOutputStream()
        val repo = git.repository

        // write info upload pack response
        byteOutputStream.write("001e# service=git-upload-pack\n".toByteArray())
        byteOutputStream.write("0000".toByteArray())

       for ((oid, ref) in repo.allRefsByPeeledObjectId) {
            log.info("oid, ref : {} {}", oid, ref)
       }

        byteOutputStream.write("0000".toByteArray())
        return byteOutputStream.toByteArray()
    }

    override fun handleReceivePack(): String {
        TODO("Not yet implemented")
    }

    override fun handleUploadPack(): String {
        TODO("Not yet implemented")
    }
}