package com.example.environ.models

data class User(val uid: String = "",
                val displayName: String? = "",
                val imageUrl: String = "",
                val user_points: Int = 0
)

//Prev:
/*
data class User(val uid: String = "",
                val displayName: String? = "",
                val imageUrl: String = "")

 */