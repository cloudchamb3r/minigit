package com.cloudchamb3r.minigit

data class GitAuthor(
    val name: String,
    val email: String,
){
    override fun toString()= "$name <$email>"
}