package com.example.androidchallenge.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.androidchallenge.data.Meditation
import com.example.androidchallenge.databinding.ItemMeditationBinding
import com.example.androidchallenge.util.setOnSafeClickListener


class MeditationAdapter(
    private val meditations: List<Meditation>,
    private val onClick: (Meditation) -> Unit
) : RecyclerView.Adapter<MeditationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemMeditationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(meditations[position])
    }

    override fun getItemCount(): Int = meditations.size

    inner class ViewHolder(private val viewBinding: ItemMeditationBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(meditation: Meditation) {
            viewBinding.apply {
                with(meditation) {
                    root.setOnSafeClickListener { onClick(meditation) }
                    meditationImageView.load(image.small) {
                        transformations(RoundedCornersTransformation(15F))
                    }
                    titleTextView.text = title
                    subtitleTextView.text = subtitle
                }
            }
        }

    }
}