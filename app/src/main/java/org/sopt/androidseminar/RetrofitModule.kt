package org.sopt.androidseminar

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import org.sopt.androidseminar.api.sopt.SoptApi
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SoptRetrofit

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    @SoptRetrofit
    fun provideSoptRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://cherishserver.com")
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideLoginApiService(@SoptRetrofit retrofit: Retrofit) : SoptApi =
        retrofit.create(SoptApi::class.java)
}

