package com.example.holmi_production.recycleview_4.mvp.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.holmi_production.recycleview_4.source.network.NewsItem

interface SingleNewsView: MvpView{
    @StateStrategyType(SkipStrategy::class)
    fun showNews(listItem: NewsItem)
    fun showToast()
}