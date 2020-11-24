package com.example.saurabh.userappmvp.dependency

import com.example.saurabh.userappmvp.app.Constant
import com.example.saurabh.userappmvp.datasource.UserRepositoryContract
import com.example.saurabh.userappmvp.datasource.local.LocalDataSource
import com.example.saurabh.userappmvp.datasource.local.LocalDbHelper
import com.example.saurabh.userappmvp.datasource.remote.RemoteDataSource
import com.example.saurabh.userappmvp.datasource.remote.RemoteDbHelper
import com.example.saurabh.userappmvp.datasource.remote.UserOperation
import com.example.saurabh.userappmvp.file.ProgressResponseBody
import com.example.saurabh.userappmvp.file.ProgressListener
import com.example.saurabh.userappmvp.file.ProgressListenerHelper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Scope


@Module
class NetworkModule {

    val progressListener: ProgressListener = object : ProgressListener {
        override fun update(bytesRead: Long, contentLength: Long, done: Boolean) {
            println(bytesRead)
            println(contentLength)
            println(done)
            System.out.format("%d%% done\n", 100 * bytesRead / contentLength)
        }
    }

    @Provides
    fun retrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constant.URL.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    @Provides
    fun httpClient(logger : HttpLoggingInterceptor) =
            OkHttpClient.Builder()
                    .readTimeout(60,TimeUnit.SECONDS)
                    .writeTimeout(60,TimeUnit.SECONDS)
                    .addNetworkInterceptor {
                        val originalResponse = it.proceed(it.request());
                        originalResponse.newBuilder().body(
                               ProgressResponseBody(originalResponse.body,ProgressListenerHelper.progressListener)
                        ).build()
                    }
                    .addInterceptor(logger).build()

    @Provides
    fun httpLoggingInterceptor() : HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BASIC
        return logger
    }

    @Provides
    fun userOperation(retrofit: Retrofit) = retrofit.create(UserOperation::class.java)

}


@Module(includes = [NetworkModule::class])
class RepositoryModule {

    @Provides @Named("local")
    fun localDataSource(localDbHelper: LocalDbHelper) : UserRepositoryContract = LocalDataSource(localDbHelper)

    @Provides @Named("remote")
    fun remoteDataSource(remoteDbHelper: RemoteDbHelper) : UserRepositoryContract = RemoteDataSource(remoteDbHelper)

}



@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class AppScope




