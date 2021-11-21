package com.example.environ.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.environ.R
import com.example.environ.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private lateinit var  profileBinding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Default: Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_profile, container, false)

        //Data binding
        profileBinding = FragmentProfileBinding.inflate(inflater, container, false)
        return profileBinding.root
    }

}