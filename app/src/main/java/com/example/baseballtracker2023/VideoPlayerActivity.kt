package com.example.baseballtracker2023


import android.os.Bundle
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView


class VideoPlayerActivity : YouTubeBaseActivity() {

    // id of the video which we are playing.
    var YOUTUBE_VIDEO_ID = "dQw4w9WgXcQ"
    private val YOUTUBE_API_KEY = "AIzaSyCIx1EVYSnhz13MJZsJA1uGDPQmYHN5jhM"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.player_activity_video)
        val ytPlayer = findViewById<YouTubePlayerView>(R.id.youtube_player_view)
        ytPlayer.initialize(YOUTUBE_API_KEY, object : YouTubePlayer.OnInitializedListener{
            // Implement two methods by clicking on red error bulb
            // inside onInitializationSuccess method
            // add the video link or the
            // playlist link that you want to play
            // In here we also handle the play and pause functionality
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider?,
                player: YouTubePlayer?,
                p2: Boolean
            ) {
                player?.loadVideo("dQw4w9WgXcQ")
                player?.play()
            }

            // Inside onInitializationFailure
            // implement the failure functionality
            // Here we will show toast
            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(this@VideoPlayerActivity , "Video player Failed" , Toast.LENGTH_SHORT).show()
            }
        })
    }
//
//        val youTubePlayerView = findViewById<YouTubePlayerView>(R.id.youtube_player_view)
//        youTubePlayerView.initialize(YOUTUBE_API_KEY, object : YouTubePlayer.OnInitializedListener{
//            // Implement two methods by clicking on red error bulb
//            // inside onInitializationSuccess method
//            // add the video link
//            override fun onInitializationSuccess(
//                provider: YouTubePlayer.Provider?,
//                player: YouTubePlayer?,
//                p2: Boolean
//            ) {
//                player?.loadVideo("dQw4w9WgXcQ")
//                player?.play()
//            }
//
//            // Inside onInitializationFailure
//            // implement the failure functionality
//            // Here we will show toast
//            override fun onInitializationFailure(
//                p0: YouTubePlayer.Provider?,
//                p1: YouTubeInitializationResult?
//            ) {
//                Toast.makeText(this@VideoPlayerActivity, "Video player Failed" , Toast.LENGTH_SHORT).show()
//            }
//        })
}


