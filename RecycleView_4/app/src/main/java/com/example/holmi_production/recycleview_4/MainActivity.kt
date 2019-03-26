package com.example.holmi_production.recycleview_4

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.holmi_production.recycleview_4.Adapters.ViewPagerAdapter
import com.example.holmi_production.recycleview_4.db.NewsRepository
import com.example.holmi_production.recycleview_4.db.entity.News
import java.security.AccessController.getContext


class MainActivity : AppCompatActivity(), ListFragment.Callbacks {

    companion object {
        const val ARG_IS_FAVORITE = "isFavorite"
        const val ARG_ID = "id"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager: ViewPager = findViewById(R.id.pager)
        setupViewPager(viewPager)
        val tabLayout: TabLayout = findViewById(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)

    }

    private fun createFavoriteFragment(): ListFragment {
        val favoriteFragment = ListFragment()
        val bundle = Bundle()
        bundle.putBoolean(ARG_IS_FAVORITE, true)
        favoriteFragment.arguments = bundle
        return favoriteFragment
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        val favoriteFragment = createFavoriteFragment()
        val lastPageName = resources.getString(R.string.lastPageName)
        val favPageName = resources.getString(R.string.favoritePageName)

        adapter.addFragment(ListFragment(), lastPageName)
        adapter.addFragment(favoriteFragment, favPageName)
        viewPager.adapter = adapter
    }

    override fun onItemClicked(v: View, news: News) {
        Log.d("RecyclerView", "CLICK!")
        val intent = Intent(v.context, ActivityItem::class.java).apply {
            putExtra(ARG_ID, news.id)
        }
        ContextCompat.startActivity(v.context, intent, null)
    }
}
