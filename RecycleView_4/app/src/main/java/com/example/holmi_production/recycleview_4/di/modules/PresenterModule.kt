package com.example.holmi_production.recycleview_4.di.modules

import android.net.ConnectivityManager
import com.example.holmi_production.recycleview_4.TypeElement.NewsFragmentPresenterImp
import com.example.holmi_production.recycleview_4.detail.SingleNewsPresenterImp
import com.example.holmi_production.recycleview_4.model.NewsRepository
import dagger.Module
import dagger.Provides


@Module
class PresenterModule{

    @Provides
    fun provideListPresenter(newsRepository: NewsRepository, cm:ConnectivityManager): NewsFragmentPresenterImp {
        return NewsFragmentPresenterImp(newsRepository, cm)
    }

    @Provides
    fun provideSinglePresenter(newsRepository: NewsRepository, cm:ConnectivityManager): SingleNewsPresenterImp {
        return SingleNewsPresenterImp(newsRepository, cm)
    }
}