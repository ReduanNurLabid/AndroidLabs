package com.example.labass2.ui.audio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.media3.common.MediaItem
import androidx.media3.common.util.Util
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerControlView
import com.example.labass2.R

@UnstableApi
class AudioFragment : Fragment() {

    private lateinit var audioViewModel: AudioViewModel
    private var player: ExoPlayer? = null
    private lateinit var playerControlView: PlayerControlView
    private lateinit var playButton: Button
    private lateinit var pauseButton: Button
    
   
    private val audioUrl = "https://download.samplelib.com/mp3/sample-15s.mp3"
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        audioViewModel =
            ViewModelProvider(this).get(AudioViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_audio, container, false)
        
        playerControlView = root.findViewById(R.id.player_control_view)
        playButton = root.findViewById(R.id.btn_play)
        pauseButton = root.findViewById(R.id.btn_pause)
        
        playButton.setOnClickListener {
            player?.play()
        }
        
        pauseButton.setOnClickListener {
            player?.pause()
        }
        
        return root
    }
    
    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            initializePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if (Util.SDK_INT <= 23 || player == null) {
            initializePlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }
    
    private fun initializePlayer() {
        player = ExoPlayer.Builder(requireContext())
            .build()
            .also { exoPlayer ->
                playerControlView.player = exoPlayer
                
                val mediaItem = MediaItem.fromUri(audioUrl)
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.prepare()
            }
    }
    
    private fun releasePlayer() {
        player?.let { exoPlayer ->
            exoPlayer.release()
        }
        player = null
    }
}