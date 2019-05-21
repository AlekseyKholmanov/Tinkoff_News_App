package com.example.holmi_production.recycleview_4.orm

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.example.holmi_production.recycleview_4.model.News
import io.reactivex.Flowable
import io.reactivex.Single


@Dao
interface NewsDao{
    @Query("SELECT * FROM News WHERE newsId=:idToSelect")
    fun getNewsById(idToSelect: Int): Single<News>

    @Query("Select * from News where newsId in (:newsIds)")
    fun getNewsByIds(newsIds:List<Int>):Flowable<List<News>>

    @Query("SELECT * FROM News")
    fun  getAllNews():Single<List<News>>

    @Insert(onConflict = REPLACE)
    fun insert(news: News)

    @Insert(onConflict = REPLACE)
    fun insertListNews(news:List<News>)

    @Delete
    fun delete(news: News)

    @Query("DELETE FROM news")
    fun deleteAll()
}