package com.eyedea.animax.android

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.media3.common.*
import androidx.media3.common.Player.Listener
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.trackselection.AdaptiveTrackSelection
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import com.eyedea.animax.android.databinding.PageMainBinding
import com.eyedea.core.base.BaseViewBindingFragment
import kotlin.math.roundToInt


@UnstableApi class MainPage(
    override val layout: Int = R.layout.page_main,
) : BaseViewBindingFragment<PageMainBinding>() {

    private var isFullScreen = false
    private val trackSelector by lazy {
        DefaultTrackSelector(requireContext(), AdaptiveTrackSelection.Factory()).apply {
            setParameters(
                buildUponParameters()
                    .setForceHighestSupportedBitrate(true)
            )
        }
    }

    override fun initBinding(view: View) {
        binding = PageMainBinding.bind(view)
    }

    override fun didMount(view: View) {
        super.didMount(view)
        val mediaItem = MediaItem.fromUri("https://api.dyntube.com/v1/apps/hls/GuVuETZg0G2FipCJ8dwog.m3u8")
        val hlsMediaSource = HlsMediaSource
            .Factory(DataSource.Factory { DefaultHttpDataSource.Factory().createDataSource() })
            .createMediaSource(mediaItem)
        val exoPlayer = ExoPlayer.Builder(requireContext())
            .setTrackSelector(trackSelector)
            .build()
        binding?.apply {
            player.apply {
                player = exoPlayer
                (player as ExoPlayer).addMediaSource(hlsMediaSource)
                player?.prepare()
                player?.playWhenReady = true
                player?.addListener(object : Listener {

                    override fun onIsPlayingChanged(isPlaying: Boolean) {
                        super.onIsPlayingChanged(isPlaying)
                        isPlaying
                    }

                    override fun onTracksChanged(tracks: Tracks) {
                        super.onTracksChanged(tracks)
                        val formats = mutableListOf<Format>()
                        tracks.groups.forEach { track ->
                            repeat(track.mediaTrackGroup.length){
                                val format = track.mediaTrackGroup.getFormat(it)
                                formats.add(format)
                            }
                        }

                        val videoFormat = formats.filter { it.sampleMimeType.toString().contains("video") }
                        val audioFormat = formats.filter { it.sampleMimeType.toString().contains("audio") }
                        binding?.testReso?.text = videoFormat.map {
                            Pair(it.width, it.height)
                        }.toString()
                        setupChangeReoslution(videoFormat, tracks)
                    }
                })
            }
        }
        setupFullScreen()
    }

    private fun setupChangeReoslution(videoFormat: List<Format>, tracks: Tracks) {
        val track = trackSelector.currentMappedTrackInfo
        track?.let {
            val trackGroup = track.getTrackGroups(0).get(0)
            val randomIndex = (0 until it.rendererCount).random()
            val format = trackGroup.getFormat(randomIndex)
            binding?.testReso?.setOnClickListener {
                Log.d("ANGGATAG", "Index: $randomIndex");
                binding?.player?.player?.apply {
                    stop()
                    trackSelectionParameters = trackSelectionParameters
                        .buildUpon()
//                        .addOverride(TrackSelectionOverride(trackGroup, randomIndex))
                        .setMaxVideoSize(format.width, format.height)
                        .build()
                    prepare()
                    playWhenReady = true
                    seekTo(currentPosition - 1000)
                }
                binding?.selectedReso?.apply {
                    val item = trackGroup.getFormat(randomIndex)
                    text = Pair(item.width, item.height).toString()
                }
            }
        }
    }

    @SuppressLint("SourceLockedOrientationActivity")
    private fun setupFullScreen() {
        val fullscreenButton = binding?.root?.findViewById<ImageView>(R.id.exo_fullscreen_button)
        val window = requireActivity().window
        fullscreenButton?.setOnClickListener {
            if (isFullScreen){
                requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                binding?.player?.apply {
                    layoutParams.height = (200 * resources.displayMetrics.density.roundToInt())
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
                }
                isFullScreen = false
            } else {
                requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                binding?.player?.apply {
                    layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
                }
                isFullScreen = true
            }
        }
    }

    private fun setVideoResolution(trackSelector : DefaultTrackSelector){
        trackSelector.setParameters(
            TrackSelectionParameters.Builder(requireContext())
                .setMaxVideoSize(1080, 1920)
                .setForceHighestSupportedBitrate(true)
                .build()
        )
    }
}