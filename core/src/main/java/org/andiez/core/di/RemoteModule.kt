package org.andiez.core.di

import org.andiez.core.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.andiez.core.remote.interceptor.HeaderInterceptor
import org.andiez.core.remote.service.AppService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by AndiezSatria on 17/04/2023.
 */

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {
    @Provides
    fun provideHeaderInterceptor(): HeaderInterceptor {
        return HeaderInterceptor()
    }

    @Provides
    fun provideOkHttpClient(headerInterceptor: HeaderInterceptor): OkHttpClient =
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(headerInterceptor)
                .build()
        } else {
            OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .build()
        }

    @Provides
    fun provideApiService(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_API)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Provides
    fun provideApiServices(retrofit: Retrofit): AppService =
        retrofit.create(AppService::class.java)
}