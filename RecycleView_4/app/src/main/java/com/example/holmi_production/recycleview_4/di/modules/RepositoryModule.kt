package com.example.holmi_production.recycleview_4.di.modules

import com.example.holmi_production.recycleview_4.source.network.RemoteDataSource
import com.example.holmi_production.recycleview_4.source.db.NewsDatabase
import com.example.holmi_production.recycleview_4.mvp.model.NewsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule{


    @Provides
    @Singleton
    fun provideRepository(newsDatabase: NewsDatabase, remoteDataSource: RemoteDataSource): NewsRepository {
        return NewsRepository(newsDatabase, remoteDataSource)
    }
}