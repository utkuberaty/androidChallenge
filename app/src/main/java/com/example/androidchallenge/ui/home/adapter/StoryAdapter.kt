package com.example.androidchallenge.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.androidchallenge.data.Meditation
import com.example.androidchallenge.data.Story
import com.example.androidchallenge.databinding.ItemStoryBinding
import com.example.androidchallenge.util.setOnSafeClickListener

class StoryAdapter(private val stories: List<Story>, private val onClick: (Story) -> Unit) :
    RecyclerView.Adapter<StoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(stories[position])
    }

    override fun getItemCount(): Int = stories.size

    inner class ViewHolder(private val viewBinding: ItemStoryBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(story: Story) {
            viewBinding.apply {
                with(story) {
                    root.setOnSafeClickListener { onClick(story) }
                    storyImageView.load(image.small) {
                        transformations(RoundedCornersTransformation(15F))
                    }
                    nameTextView.text = name
                    categoryTextView.text = category
                }
            }
        }

    }
}