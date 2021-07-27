package com.example.androidchallenge.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidchallenge.R
import com.example.androidchallenge.base.BaseFragment
import com.example.androidchallenge.base.BaseViewModel
import com.example.androidchallenge.data.Meditation
import com.example.androidchallenge.data.Result
import com.example.androidchallenge.data.Story
import com.example.androidchallenge.databinding.FragmentHomeBinding
import com.example.androidchallenge.ui.home.adapter.MeditationAdapter
import com.example.androidchallenge.ui.home.adapter.StoryAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.serialization.StringFormat
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>({ FragmentHomeBinding.inflate(it) }) {

    private val viewModel: HomeViewModel by viewModel()

    private val navHostController by lazy { findNavController() }

    private val storyList = mutableListOf<Story>()
    private val meditationList = mutableListOf<Meditation>()
    private var data: Result? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setMeditationsRecyclerView()
        setStoriesRecyclerView()
        fetchData()
    }

    private fun setBanner() {
        viewBinding.bannerConstraintLayout.apply {
            if (data?.isBannerEnabled == true) {
                visibility = View.VISIBLE
                viewBinding.bannerTextView.text = getString(
                    R.string.banner_text,
                    viewModel.getAuthData().username
                )
            } else visibility = View.GONE
        }
    }

    private fun fetchData() {
        lifecycleScope.launch {
            viewModel.getLocalData().collect {
                data = it
                setBanner()
            }
            fetchStories()
            fetchMeditations()
        }
    }

    private suspend fun fetchStories() {
        viewModel.getStories().collect {
            if (it.isNullOrEmpty()) errorMessage(getString(R.string.empty_data_error))
            else {
                storyList.addAll(it)
                viewBinding.storiesRecyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }

    private suspend fun fetchMeditations() {
        viewModel.getMeditations().collect {
            if (it.isNullOrEmpty()) errorMessage(getString(R.string.empty_data_error))
            else {
                meditationList.addAll(it)
                viewBinding.meditationsRecyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }

    private fun setMeditationsRecyclerView() {
        viewBinding.meditationsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            adapter = MeditationAdapter(meditationList) {
                navHostController.navigate(
                    HomeFragmentDirections.actionHomeFragmentToMediaDetailFragment(
                        meditation = it
                    )
                )
            }
        }
    }

    private fun setStoriesRecyclerView() {
        viewBinding.storiesRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = StoryAdapter(storyList) {
                navHostController.navigate(
                    HomeFragmentDirections.actionHomeFragmentToMediaDetailFragment(
                        story = it
                    )
                )
            }
        }
    }
}