package com.example.environ.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.util.Log
//import android.view.Menu
//import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import com.example.environ.R
import com.example.environ.SignInActivity
import com.example.environ.databinding.ActivitySettingsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

//    private companion object{
//        private const val TAG= "SettingsActivity"
//    }
    private lateinit var auth: FirebaseAuth
    private lateinit var settingsBinding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        auth = Firebase.auth

        val logout = findViewById<Button>(R.id.activity_settings_logout)
        logout.setOnClickListener {
            //Log.i(SettingsActivity.TAG, "Logout")
            auth.signOut()
            val logoutIntent= Intent(this, SignInActivity::class.java)
            logoutIntent.flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(logoutIntent)
        }

        activity_settings_back.setOnClickListener {
            super.onBackPressed()
        }

    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(item.itemId == R.id.mi_logout){
//            Log.i(SettingsActivity.TAG, "Logout")
//            auth.signOut()
//            val logoutIntent= Intent(this, SignInActivity::class.java)
//            logoutIntent.flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            startActivity(logoutIntent)
//        }
//
//        return super.onOptionsItemSelected(item)
//    }
}