package com.cloudchamb3r.minigit.git.transfer

import org.springframework.stereotype.Component
import java.io.File

class JGitTransfer: GitTransfer {
    override fun on(dir: File): GitTransfer {
        TODO("Not yet implemented")
    }

    override fun infoRefs(): String {
        TODO("Not yet implemented")
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

    override fun gitReceivePack(): String {
        TODO("Not yet implemented")
    }

    override fun gitUploadPack(): String {
        TODO("Not yet implemented")
    }
}