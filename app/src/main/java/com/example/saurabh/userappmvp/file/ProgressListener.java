package com.example.saurabh.userappmvp.file;

public interface ProgressListener {
    void update(long bytesRead, long contentLength, boolean done);
  }