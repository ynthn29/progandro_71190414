package com.example.pertemuan8_toolbar_71190414

import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.ListFragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import java.text.FieldPosition

class HomeActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar_default))
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val viewPager = findViewById<ViewPager2>(R.id.pager)
        val listFragment: ArrayList<Fragment> = arrayListOf(SatuFragment(),DuaFragment())
        val adapter = PagerAdapter(this, listFragment)

        viewPager.adapter = adapter
        viewPager.setCurrentItem(0)
    }

    class PagerAdapter(val fa: FragmentActivity, val listFragment: ArrayList<Fragment>): FragmentStateAdapter(fa){
        override fun getItemCount(): Int = listFragment.size
        override fun createFragment(position: Int): Fragment = listFragment[position]

        }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_default, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.love -> {
//                Toast.makeText(this,"ini menu profile", Toast.LENGTH_SHORT).show()
                val viewPager = findViewById<ViewPager2>(R.id.pager)
                viewPager.setCurrentItem(0)
                true
            }
            R.id.message -> {
//                Toast.makeText(this,"ini menu setting", Toast.LENGTH_SHORT).show()
                val viewPager = findViewById<ViewPager2>(R.id.pager)
                viewPager.setCurrentItem(1)
                true
            }
        }

        return super.onOptionsItemSelected(item)
    }

}

