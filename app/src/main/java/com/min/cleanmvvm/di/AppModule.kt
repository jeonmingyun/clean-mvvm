package com.min.cleanmvvm.di

import android.content.Context
import androidx.room.Room
import com.min.cleanmvvm.data.api.UserApiService
import com.min.cleanmvvm.data.db.AppDatabase
import com.min.cleanmvvm.data.db.UserDao
import com.min.cleanmvvm.data.repository.UserRepositoryImpl
import com.min.cleanmvvm.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Retrofit 제공
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://mock.api.com/") // 실제 사용되지 않는 Base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // UserApiService 제공
    @Provides
    @Singleton
    fun provideUserApiService(retrofit: Retrofit): UserApiService {
        return retrofit.create(UserApiService::class.java)
    }

    // Room Database 제공
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "user_database"
        ).build()
    }

    // UserDao 제공
    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    // UserRepository 인터페이스에 대한 구현체 제공
    @Provides
    @Singleton
    fun provideUserRepository(repositoryImpl: UserRepositoryImpl): UserRepository {
        return repositoryImpl
    }
}
