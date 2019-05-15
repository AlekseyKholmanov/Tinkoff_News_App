package com.example.holmi_production.recycleview_4.orm

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.holmi_production.recycleview_4.model.FavoriteNews
import com.example.holmi_production.recycleview_4.model.News
import com.example.holmi_production.recycleview_4.model.ViewedContent

@Database(entities = [News::class, FavoriteNews::class, ViewedContent::class], version = 9, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
    abstract fun favoriteNewsDao(): FavoriteNewsDao
    abstract fun favorite(): FavoriteDao
    abstract fun viewedNews(): ViewedNewsDao
}
