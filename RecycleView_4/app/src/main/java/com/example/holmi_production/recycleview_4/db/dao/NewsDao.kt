package com.example.holmi_production.recycleview_4.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.support.annotation.Nullable
import com.example.holmi_production.recycleview_4.db.entity.News
import io.reactivex.Completable
import io.reactivex.Single


@Dao
public interface NewsDao{
    @Nullable
    @Query("SELECT * FROM news WHERE id=:idToSelect")
    fun getNewsById(idToSelect: Int): News

    @Query("Select * from news where id in (:newsIds)")
    fun getNewsByIds(newsIds:Array<Int>):Single<List<News>>

    @Query("SELECT * FROM news")
    fun  getAll():Single<List<News>>

    @Insert(onConflict = REPLACE)
    fun insert(news: News)

    @Insert
    fun insertData(news:List<News>)

    @Delete
    fun delete(news: News)

    @Update
    fun update(news: News)

    @Query("DELETE FROM news")
    fun deleteAll()
}