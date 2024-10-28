package com.cloudchamb3r.minigit.git.transfer

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.transport.PacketLineOut
import org.eclipse.jgit.transport.ReceivePack
import org.eclipse.jgit.transport.RefAdvertiser
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.BufferedInputStream
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.charset.Charset

class JGitTransfer: GitTransfer {
    companion object {
        private val log = LoggerFactory.getLogger(JGitTransfer::class.java)
    }

    private lateinit var git : Git

    override fun on(dir: File): GitTransfer {
        git = Git.open(dir)
        return this
    }

    override fun infoRefs(): String {
        return git.repository.refDatabase.refs.joinToString { "," }
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

    override fun infoReceivePack(): String {
        val receivePack = ReceivePack(git.repository)
        val byteOutStream = ByteArrayOutputStream()
        val packetLineOut = PacketLineOut(byteOutStream)
        val advertiser = RefAdvertiser.PacketLineOutRefAdvertiser(packetLineOut)
        receivePack.sendAdvertisedRefs(advertiser)
        return byteOutStream.toString(Charset.defaultCharset())
    }

    override fun infoUploadPack(): String {
        TODO("Not yet implemented")
    }

    override fun handleReceivePack(): String {
        TODO("Not yet implemented")
    }

    override fun handleUploadPack(): String {
        TODO("Not yet implemented")
    }
}