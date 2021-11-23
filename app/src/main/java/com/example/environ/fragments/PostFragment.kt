package com.example.environ.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.environ.CreatePostActivity
import com.example.environ.daos.PostDao
import com.example.environ.databinding.FragmentPostBinding
import com.example.environ.models.Post
import com.example.environ.posts.IPostAdapter
import com.example.environ.posts.PostAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.synthetic.main.fragment_post.*
import com.google.firebase.firestore.Query


class PostFragment : Fragment(), IPostAdapter {

    private lateinit var postDao: PostDao
    private lateinit var adapter: PostAdapter

    //binding var below:
    private lateinit var postBinding: FragmentPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_post, container, false)
        postBinding = FragmentPostBinding.inflate(inflater, container, false)
        return postBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //Added button on click part
        //val fab = getView()?.findViewById<Button>(R.id.fab)

        fab.setOnClickListener {

            val intent = Intent(activity, CreatePostActivity::class.java) //this to activity
            startActivity(intent)

        }

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        postDao = PostDao()
        val postsCollections = postDao.postCollections
        val query = postsCollections.orderBy("createdAt", Query.Direction.DESCENDING)
        val recyclerViewOptions = FirestoreRecyclerOptions.Builder<Post>().setQuery(query, Post::class.java).build()

        adapter = PostAdapter(recyclerViewOptions, this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity) //changed this to activity
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    override fun onLikeClicked(postId: String) {
        postDao.updateLikes(postId)
    }

}
