package com.amirhusseinsoori.simple_ktor_mvvm.data.di

import com.amirhusseinsoori.simple_ktor_mvvm.data.network.repository.UsersRepository
import com.amirhusseinsoori.simple_ktor_mvvm.data.network.repository.UsersRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepositoryModule(usersRepositoryImp: UsersRepositoryImp): UsersRepository
}