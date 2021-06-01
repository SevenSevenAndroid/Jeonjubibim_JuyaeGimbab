package org.sopt.androidseminar.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.androidseminar.login.LoginDataSource
import org.sopt.androidseminar.login.LoginRepository
import org.sopt.androidseminar.login.LoginRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideLoginRepository(loginDataSource : LoginDataSource) : LoginRepository
    = LoginRepositoryImpl(loginDataSource)
}