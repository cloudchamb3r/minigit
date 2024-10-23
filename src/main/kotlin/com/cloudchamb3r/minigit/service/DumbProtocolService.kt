package com.cloudchamb3r.minigit.service

import com.cloudchamb3r.minigit.git.client.GitClients
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.util.Assert

@Service
class DumbProtocolService(
    private val gitClients: GitClients,
) {
    companion object {
        private val log = LoggerFactory.getLogger(DumbProtocolService::class.java)
        private const val CLIENT_NOT_FOUND_ERR = "client should not be null"
    }
    fun getRefs(owner: String, repo: String): String {
        TODO("not implemented yet")
    }

    fun getHeads(owner: String, repo: String): String {
        TODO("not implemented yet")
    }
}