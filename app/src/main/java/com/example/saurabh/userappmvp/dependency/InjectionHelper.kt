package com.example.saurabh.userappmvp.dependency

import com.example.saurabh.userappmvp.app.Constant
import com.example.saurabh.userappmvp.datasource.UserRepository
import com.example.saurabh.userappmvp.datasource.UserRepositoryContract
import com.example.saurabh.userappmvp.datasource.local.LocalDataSource
import com.example.saurabh.userappmvp.datasource.local.LocalDbHelper
import com.example.saurabh.userappmvp.datasource.remote.RemoteDataSource
import com.example.saurabh.userappmvp.datasource.remote.RemoteDbHelper
import com.example.saurabh.userappmvp.datasource.remote.UserOperation
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Scope


@Module
class NetworkModule {

    @Provides
    fun retrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constant.URL.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    @Provides
    fun httpClient(logger : HttpLoggingInterceptor) =
            OkHttpClient.Builder().addInterceptor(logger).build()

    @Provides
    fun httpLoggingInterceptor() : HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
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




