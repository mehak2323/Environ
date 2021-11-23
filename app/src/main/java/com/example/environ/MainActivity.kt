package com.example.environ

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import androidx.navigation.findNavController
//import androidx.navigation.ui.AppBarConfiguration
//import androidx.navigation.ui.setupActionBarWithNavController
//import androidx.navigation.ui.setupWithNavController
//import com.example.environ.databinding.ActivityMainBinding
//import com.google.android.material.bottomnavigation.BottomNavigationView
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

    //private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Navigation bottom nav..

        replaceFragment(taskFragment)
        nav_view.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_task -> replaceFragment(taskFragment)
                R.id.navigation_profile -> replaceFragment(profileFragment)
                R.id.navigation_dashboard -> replaceFragment(dashboardFragment)
                R.id.navigation_posts -> replaceFragment(postFragment)
            }
            true
        }
        //..bottom nav finished


        //Bottom nav with binding here.. // TODO: Not working
//        mainBinding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(mainBinding.root)
//
//        val navView: BottomNavigationView = mainBinding.navView
//
//        val navController = findNavController(R.id.fragment_container)
//        // Passing each menu ID as a set of Ids because each menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(setOf(
//            R.id.navigation_task, R.id.navigation_dashboard, R.id.navigation_posts, R.id.navigation_profile))
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
        //..to here

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