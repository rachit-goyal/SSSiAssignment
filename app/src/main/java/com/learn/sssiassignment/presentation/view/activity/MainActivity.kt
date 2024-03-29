package com.learn.sssiassignment.presentation.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.learn.sssiassignment.R
import com.learn.sssiassignment.adapters.ViewPagerAdapter
import com.learn.sssiassignment.databinding.ActivityMainBinding
import com.learn.sssiassignment.presentation.view.fragment.FavouriteFragment
import com.learn.sssiassignment.presentation.view.fragment.TeachersFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)
        mainBinding.apply {
            setupViewPager(tabViewpager)
            tabTablayout.setupWithViewPager(tabViewpager)
        }

    }



    private fun setupViewPager(viewpager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(TeachersFragment(), getString(R.string.teachers))
        adapter.addFragment(FavouriteFragment(), getString(R.string.favourite))
        viewpager.adapter = adapter
    }


}
