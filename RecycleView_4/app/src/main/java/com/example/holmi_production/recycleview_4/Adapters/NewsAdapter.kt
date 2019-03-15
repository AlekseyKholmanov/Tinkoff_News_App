package com.example.holmi_production.recycleview_4.Adapters

import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.holmi_production.recycleview_4.ActivityItem
import com.example.holmi_production.recycleview_4.Interfaces.mClickListener
import com.example.holmi_production.recycleview_4.Model.News
import com.example.holmi_production.recycleview_4.NewsItems.HeaderItem
import com.example.holmi_production.recycleview_4.NewsItems.ListItem
import com.example.holmi_production.recycleview_4.NewsItems.NewsItem
import com.example.holmi_production.recycleview_4.R
import com.example.holmi_production.recycleview_4.utils.DateUtils

class NewsAdapter(private val listItem: List<ListItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),mClickListener  {
    override fun mClick(v: View, position: Int) {
        
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ListItem.TYPE_HEADER -> {
                val itemView = inflater.inflate(R.layout.view_list_item_header, parent, false)
                HeaderViewHolder(itemView)
            }
            ListItem.TYPE_NEWS -> {
                val itemView = inflater.inflate(R.layout.view_list_item_news, parent, false)
                itemView.setOnClickListener()
                NewsViewHolder(itemView)
            }
            else -> throw IllegalStateException("unsupported item type")
        }
    }

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        var viewType = getItemViewType(position)
        val currentDay = DateUtils().currentDay()
        when (viewType) {
            ListItem.TYPE_HEADER -> {
                var headerItem = listItem[position] as HeaderItem
                var viewHolder = viewHolder as HeaderViewHolder
                var dateText = when (headerItem.date) {
                    DateUtils().buildDate(currentDay) -> {
                        "Сегодня"
                    }
                    DateUtils().buildDate(currentDay - 1) -> {
                        "Вчера"
                    }
                    else -> {
                        DateUtils().formatDate(headerItem.date)
                    }
                }
                viewHolder.txt_header.text = dateText
            }
            ListItem.TYPE_NEWS -> {
                var newsItem = listItem[position] as NewsItem
                var viewHolder = viewHolder as NewsViewHolder
                viewHolder.bind(newsItem.content)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return listItem[position].getType()
    }

    private class HeaderViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var txt_header: TextView = itemView.findViewById(R.id.txt_header)

    }

    private class NewsViewHolder internal constructor(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private val theme = v.findViewById<TextView>(R.id.theme)
        private val content = v.findViewById<TextView>(R.id.content)
        private var date = ""
        private var isFavorite = false

        init {
            v.setOnClickListener(this)
        }

        fun bind(news: News) {
            theme.text = news.theme
            date = DateUtils().formatDate(news.date)
            content.text = news.content
            isFavorite = news.isFavorites
        }

        override fun onClick(v: View) {

            val intent = Intent(v.context, ActivityItem::class.java).apply {
                putExtra("theme", theme.text)
                putExtra("content", content.text)
                putExtra("date", date)
                putExtra("isFavorite", isFavorite)
            }
            startActivity(v.context, intent, null)
            Log.d("RecyclerView", "CLICK!")
        }
    }
}
