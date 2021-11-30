package com.example.environ.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.environ.databinding.FragmentProfileBinding
import com.example.environ.models.User
import com.example.environ.profile.SettingsActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.fragment_profile.*
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.environ.profile.EditProfileActivity
import com.google.firebase.database.ktx.getValue


class ProfileFragment : Fragment() {

    private lateinit var  profileBinding: FragmentProfileBinding

    //private lateinit var databaseReference: DatabaseReference
    //private var storageReference : StorageReference? = null
    private lateinit var uid : String
    private lateinit var user : User
    private lateinit var auth : FirebaseAuth


    private lateinit var preferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //useless code to minimize stack size
//        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
//            getSupportFragmentManager().popBackStack();
//        }

        //Data binding
        profileBinding = FragmentProfileBinding.inflate(inflater, container, false)
        return profileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //shared preference code
        preferences= activity?.getSharedPreferences("userPref", Context.MODE_PRIVATE)!!

        profile_points.text = preferences.getInt("point",0).toString()
        profile_level.text = preferences.getInt("level",0).toString()
        //TODO: share pref for location and about here
        profile_address.text = preferences.getString("location","Your location")
        profile_about.text = preferences.getString("about", "Get started and start completing challenges to live a sustainable lifestyle.")


        //Code to load image and name
        auth = Firebase.auth
        val currentUser = auth.currentUser
        profile_name.text = currentUser!!.displayName
        Glide.with(profile_user_image.context).load(currentUser.photoUrl).circleCrop().into(profile_user_image)

        //Settings Button
        profile_settings.setOnClickListener {
            val intentSettings = Intent(activity, SettingsActivity::class.java)
            startActivity(intentSettings)
        }

        //Edit Button
        profile_edit.setOnClickListener {
            val intentEdit = Intent(activity, EditProfileActivity::class.java)
            startActivity(intentEdit)
        }

    }


}


/*
// TODO: Failed firebase code attempt, write in OnViewCreated

//        uid = currentUser.uid
//
//        databaseReference = Firebase.database.reference
//        databaseReference.child("users").child(uid).get().addOnSuccessListener {
//            Log.i("firebase", "Got value ${it.value}")
//        }.addOnFailureListener{
//            Log.e("firebase", "Error getting data", it)
//        }

        //changed tutorial code a lil:
        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()
//        Toast.makeText(activity, "Uid is $uid",Toast.LENGTH_SHORT).show()


//        databaseReference = Firebase.database.reference
//
//        val profileValListener = object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                // Get Post object and use the values to update the UI
//                user = dataSnapshot.getValue<User>() as User
//
//                profile_name.text = user.displayName
//                profile_points.text = user.user_points.toString()
//                // ...
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                // Getting Post failed, log a message
//                Toast.makeText(activity, "Failed to get data",Toast.LENGTH_SHORT).show()
//            }
//        }
//        databaseReference.addValueEventListener(profileValListener)


        //Original, from tutorial : https://www.youtube.com/watch?v=_DtbxQ9EEhc


        //databaseReference = FirebaseDatabase.getInstance().getReference("users")
        databaseReference = Firebase.database.reference //changed this line and added .child("users") to dbref

        if(uid.isNotEmpty()){
            databaseReference.child("users").child(uid).addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    user = snapshot.getValue(User::class.java)!!

                    //profile_name.text = user.displayName
                    profile_points.text = user.user_points.toString()

                    Log.i("firebase", "Got value from user ${user.user_points}")
                    Toast.makeText(activity, "On data change executed",Toast.LENGTH_SHORT).show()

//                    Glide.with(profile_user_image.context)
//                        .load(user.imageUrl).apply(RequestOptions().circleCrop())
//                        .into(profile_user_image)

                    //storageReference = FirebaseStorage.getInstance().reference.child(user.imageUrl)
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.i("firebase", "Error getting points data")
                    Toast.makeText(activity, "Failed to get data",Toast.LENGTH_SHORT).show()
                }

            })
        }
 */