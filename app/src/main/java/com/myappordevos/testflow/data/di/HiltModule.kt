package com.myappordevos.testflow.data.di

import com.myappordevos.testflow.RepoGetter
import com.myappordevos.testflow.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class HiltModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =  OkHttpClient.Builder()
        .addInterceptor {
            val authentication = it.request().newBuilder()
                .addHeader("X-Auth-Token", "925562f098ce45159cab9de9d6c60128")
                .build()
            it.proceed(authentication)
        }
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("http://api.football-data.org/v4/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRepoGetter(apiService: ApiService): RepoGetter {
        return Repository(apiService)
    }
}