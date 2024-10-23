package com.cloudchamb3r.minigit.git.transfer

fun interface GitTransferSupplier {
    fun get(): GitTransfer
}