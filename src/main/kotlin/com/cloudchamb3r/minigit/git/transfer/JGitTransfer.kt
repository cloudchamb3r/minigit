package com.cloudchamb3r.minigit.git.transfer

import org.eclipse.jgit.api.Git
import org.slf4j.LoggerFactory
import java.io.File

class JGitTransfer : GitTransfer {
    companion object {
        private val log = LoggerFactory.getLogger(JGitTransfer::class.java)
    }

    private lateinit var git: Git

    override fun on(dir: File): GitTransfer {
        git = Git.open(dir)
        return this
    }

    override fun infoRefs(): Sequence<ByteArray> = sequence {
        yield(git.repository.refDatabase.refs.joinToString { "\n" }.toByteArray())
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

    override fun infoReceivePack(): Sequence<ByteArray> = sequence {

        // write info receive pack response
        yield("001f# service=git-receive-pack\n".toByteArray())
        yield("0000".toByteArray())

//        val receivePack = ReceivePack(git.repository)
//        val advertiser = RefAdvertiser.PacketLineOutRefAdvertiser(PacketLineOut(byteOutputStream))
//        receivePack.sendAdvertisedRefs(advertiser)

        yield("0000".toByteArray())
    }

    override fun infoUploadPack(): Sequence<ByteArray> = sequence {
        log.info("info upload pack called")
        val repo = git.repository

        // write info upload pack response
        log.info("yield content header")
        yield("001e# service=git-upload-pack\n".toByteArray())

        log.info("yield start flush")
        yield("0000".toByteArray())

        for ((oid, ref) in repo.allRefsByPeeledObjectId) {
            log.info("oid, ref : {} {}", oid, ref)
        }

        log.info("yield end flush")
        yield("0000".toByteArray())
    }

    override fun handleReceivePack(): String {
        TODO("Not yet implemented")
    }

    override fun handleUploadPack(): String {
        TODO("Not yet implemented")
    }

}