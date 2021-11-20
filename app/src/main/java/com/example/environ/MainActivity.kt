package com.example.environ

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.environ.fragments.DashboardFragment
import com.example.environ.fragments.PostFragment
import com.example.environ.fragments.ProfileFragment
import com.example.environ.fragments.TaskFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val taskFragment = TaskFragment()
    private val profileFragment = ProfileFragment()
    private val dashboardFragment = DashboardFragment()
    private val postFragment = PostFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Navigation bottom nav..
        replaceFragment(taskFragment)
        bottom_navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_task -> replaceFragment(taskFragment)
                R.id.navigation_profile -> replaceFragment(profileFragment)
                R.id.navigation_dashboard -> replaceFragment(dashboardFragment)
                R.id.navigation_posts -> replaceFragment(postFragment)
            }
            true
        }
        //..bottom nav finished

    }

    //Bottom Nav helper function
    private fun replaceFragment(fragment: Fragment){
        if(fragment!=null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}