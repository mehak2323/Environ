package com.example.environ.profile

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.environ.R
import com.example.environ.databinding.ActivityEditProfileBinding
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfileActivity : AppCompatActivity() {

    private lateinit var editProfileBinding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        //shared preference...
        //Creating preference here
        val sharedPreference = this.getSharedPreferences("userPref", Context.MODE_PRIVATE)
        //..here

        activity_edit_back.setOnClickListener {
            super.onBackPressed()
        }

        edit_data_button.setOnClickListener{
            val location = edit_location_edit_text.text
            val about = edit_about_edit_text.text

            if(location.isNotEmpty() and about.isNotEmpty()) {

                // TODO: share pref added here
                val editor = sharedPreference?.edit()

                editor?.apply {
                    putString("location", location.toString())
                    putString("about", about.toString())
                    apply()
                }

                super.onBackPressed()
                Toast.makeText(this, "Details are edited. Switch tabs to see.",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Please enter both fields.",Toast.LENGTH_SHORT).show()
            }
        }

    }
}