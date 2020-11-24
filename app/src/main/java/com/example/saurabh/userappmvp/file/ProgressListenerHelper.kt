package com.example.saurabh.userappmvp.file

val progressListener: ProgressListener = object : ProgressListener {
    override fun update(bytesRead: Long, contentLength: Long, done: Boolean) {
        println(bytesRead)
        println(contentLength)
        println(done)
        System.out.format("%d%% done\n", 100 * bytesRead / contentLength)
    }
}

object ProgressListenerHelper {
    var progressListener : ProgressListener ?= null

}