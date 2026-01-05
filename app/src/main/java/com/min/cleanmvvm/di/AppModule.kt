package com.min.cleanmvvm.di

import com.min.cleanmvvm.data.datasource.UserRemoteDataSource
import com.min.cleanmvvm.data.repository.UserRepositoryImpl
import com.min.cleanmvvm.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideUserRepository(
        remote: UserRemoteDataSource
    ): UserRepository {
        return UserRepositoryImpl(remote)
    }
}
