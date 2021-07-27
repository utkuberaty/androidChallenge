package com.example.androidchallenge.ui.media_detail

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import coil.load
import com.example.androidchallenge.R
import com.example.androidchallenge.base.BaseFragment
import com.example.androidchallenge.data.Meditation
import com.example.androidchallenge.data.Story
import com.example.androidchallenge.databinding.FragmentMediaDetailBinding
import java.text.SimpleDateFormat
import java.util.*

class MediaDetailFragment : BaseFragment<FragmentMediaDetailBinding>
    ({ FragmentMediaDetailBinding.inflate(it) }) {

    private val navHostController by lazy { findNavController() }

    private val args: MediaDetailFragmentArgs by navArgs()

    private val meditation: Meditation? by lazy { args.meditation }

    private val story: Story? by lazy { args.story }

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        player("https://d2r0ihkco3hemf.cloudfront.net/bgxupraW2spUpr_y2.mp3")
        setToolbar()
        setUi()
    }

    override fun onPause() {
        super.onPause()
        pausePlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }

    private fun setToolbar() {
        val appBarConfiguration = AppBarConfiguration(navHostController.graph)
        viewBinding.toolbar.setupWithNavController(navHostController, appBarConfiguration)
    }

    private fun setUi() {
        meditation?.let { setMeditationUi(it) }
        story?.let { setStoryUi(it) }
        playButton()
    }

    private fun playButton() {
        viewBinding.playerControlImageView.apply {
            setOnClickListener {
                if (mediaPlayer?.isPlaying == true) pausePlayer()
                else playPlayer()
            }
        }
    }

    private fun pausePlayer() {
        mediaPlayer?.pause()
        viewBinding.playerControlImageView.setImageResource(R.drawable.ic_play)
    }

    private fun playPlayer() {
        mediaPlayer?.start()
        viewBinding.playerControlImageView.setImageResource(R.drawable.ic_pause)
    }

    private fun setMeditationUi(meditation: Meditation) {
        viewBinding.apply {
            with(meditation) {
                toolbar.title = getString(R.string.meditation_detail)
                backgroundImageView.load(image.large)
                textTextView.text = content
                titleTextView.text = title
                dateTextView.text = convertLongToTime(releaseDate.toLong() * 1000L)
            }
        }
    }

    private fun setStoryUi(story: Story) {
        viewBinding.apply {
            with(story) {
                toolbar.title = getString(R.string.stroy_detail)
                backgroundImageView.load(image.large)
                textTextView.text = text
                titleTextView.text = name
                dateTextView.text = convertLongToTime(date.toLong() * 1000L)
            }
        }
    }

    private fun player(url: String) {
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(url)
            prepareAsync()
        }
    }

    private fun convertLongToTime(time: Long): String {
        val date = Calendar.getInstance()
        date.timeInMillis = time
        val format = SimpleDateFormat("MM/dd/yyyy, EEE", Locale.getDefault())
        return format.format(date.time)
    }

}