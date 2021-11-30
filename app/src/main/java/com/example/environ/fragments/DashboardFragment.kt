package com.example.environ.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import com.example.environ.dashboard.NewsActivity
import com.example.environ.dashboard.SortMeActivity
import com.example.environ.databinding.FragmentDashboardBinding
import kotlinx.android.synthetic.main.fragment_dashboard.*


class DashboardFragment : Fragment() {

    lateinit var dashboardBinding: FragmentDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Data binding part
        dashboardBinding = FragmentDashboardBinding.inflate(inflater, container, false)
        return dashboardBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Sort button
        fragment_dashboard_sort_button.setOnClickListener {
            val intentSort = Intent(activity, SortMeActivity::class.java)
            startActivity(intentSort)
        }

        //News Button
        fragment_dashboard_news_button.setOnClickListener {
            val intentNews = Intent(activity, NewsActivity::class.java)
            startActivity(intentNews)
        }

        //AQI Button
        fragment_dashboard_aqi_button.setOnClickListener {
            val aqiUrl ="https://waqi.info/"
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            activity?.let { it1 -> customTabsIntent.launchUrl(it1, Uri.parse(aqiUrl)) }
        }

        //Petition button
        fragment_dashboard_petition_button.setOnClickListener {
            val petitionUrl = "https://www.change.org/t/environment-18?source_location=homepage"
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            activity?.let { it1 -> customTabsIntent.launchUrl(it1, Uri.parse(petitionUrl)) }
        }

        //Movie/Documentary button
        fragment_dashboard_movie_button.setOnClickListener {
            val movieUrl = "https://en.wikipedia.org/wiki/List_of_environmental_films"
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            activity?.let { it1 -> customTabsIntent.launchUrl(it1, Uri.parse(movieUrl)) }
        }

        //List of organizations button
        fragment_dashboard_organization_button.setOnClickListener {
            val organizationUrl = "https://en.wikipedia.org/wiki/List_of_environmental_organizations"
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            activity?.let { it1 -> customTabsIntent.launchUrl(it1, Uri.parse(organizationUrl)) }
        }

    }
}