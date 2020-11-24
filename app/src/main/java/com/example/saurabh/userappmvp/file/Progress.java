package com.example.saurabh.userappmvp.file;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

public final class Progress {

  public void run() throws Exception {
    Request request = new Request.Builder()
        .url("https://publicobject.com/helloworld.txt")
        .build();

    final ProgressListener progressListener = new ProgressListener() {
      @Override public void update(long bytesRead, long contentLength, boolean done) {
        System.out.println(bytesRead);
        System.out.println(contentLength);
        System.out.println(done);
        System.out.format("%d%% done\n", (100 * bytesRead) / contentLength);
      }
    };

    OkHttpClient client = new OkHttpClient.Builder()
        .addNetworkInterceptor(new Interceptor() {
          @Override public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            return originalResponse.newBuilder()
                .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                .build();
          }
        })
        .build();

    Response response = client.newCall(request).execute();
    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

    System.out.println(response.body().string());
  }

  public static void main(String... args) throws Exception {
    new Progress().run();
  }



}

