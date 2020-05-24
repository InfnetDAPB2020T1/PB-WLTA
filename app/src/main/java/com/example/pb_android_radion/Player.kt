package com.example.pb_android_radion

import android.annotation.SuppressLint
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.SeekBar
import com.example.pb_android_radion.viewModel.MusicaViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.fragment_player.*

class Player(mediaPlayer: View, playBtn :View) {
//    private lateinit var musicaViewModel: MusicaViewModel
//    private var _totalTime: Int = 0
//    private var _positionTrack: Int = 0
//    private val mediaPlayer = MediaPlayer()
//    val firebaseStore = FirebaseFirestore.getInstance()
//
//    // private var lista: MutableList<StorageReference> = mutableListOf()
//    lateinit var storageReference: StorageReference
//    lateinit var firebaseStorage: FirebaseStorage
//
//    var musicaNome = ""
//    var musicaUrl = ""
//    firebaseStorage = getInstance()
//    storageReference = firebaseStorage.reference.child("Audio")
//    storageReference.listAll().addOnSuccessListener
//    {
//        listResult ->
//        listResult.items.forEach { item ->
//            //  lista.add(item)
//            musicaNome = item.name
//            item.downloadUrl.addOnSuccessListener {
//                //  musicaUrl = item.downloadUrl.toString()
//                //   musicaViewModel.musica?.nomeMusica
//                //Log.i("user", it.downloadUrl.toString())
//            }
//            //  url(item.downloadUrl.toString())
//            // infosMusica(musicaNome, musicaUrl)
//        }
//
//
////POSICAO DA LISTA
//        initMusicPlayer(_positionTrack)
//
//// PROGRESS BAR
//        positionBar.setOnSeekBarChangeListener(
//            object : SeekBar.OnSeekBarChangeListener {
//                override fun onProgressChanged(
//                    seekBar: SeekBar?,
//                    progress: Int,
//                    fromUser: Boolean
//                ) {
//                    if (fromUser) {
//                        mediaPlayer.seekTo(progress)
//                    }
//                }
//
//                override fun onStartTrackingTouch(p0: SeekBar?) {
//                }
//
//                override fun onStopTrackingTouch(p0: SeekBar?) {
//                }
//            }
//        )
//        fun createTimeLabel(time: Int): String {
//            var timeLabel = ""
//            var min = time / 1000 / 60
//            var sec = time / 1000 % 60
//
//            timeLabel = "$min:"
//            if (sec < 10) timeLabel += "0"
//            timeLabel += sec
//
//            return timeLabel
//        }
//
//        @SuppressLint("HandlerLeak")
//        var handler = object : Handler() {
//            override fun handleMessage(msg: Message) {
//                var currentPosition = msg.what
//                //Update positionBar
//                positionBar.progress = currentPosition
//                //Update Labels
//                var elapsedTime = createTimeLabel(currentPosition)
//                elapsedTimeLabel.text = elapsedTime
//
//                var remainingTime = createTimeLabel(_totalTime - currentPosition)
//                remainingTimeLabel.text = remainingTime
//            }
//        }
//
//// Thread
//        Thread(Runnable {
//            while (true) {
//                try {
//                    var msg = Message()
//                    msg.what = mediaPlayer.currentPosition
//                    handler.sendMessage(msg)
//                    Thread.sleep(1000)
//                } catch (e: InterruptedException) {
//                }
//            }
//        }).start()
//
//
//    }
//
//    private fun initMusicPlayer(positionTrack: Int) {
//        var listaUrl = listOf<String>(
//            "https://firebasestorage.googleapis.com/v0/b/kotlin-music-player.appspot.com/o/songs%2FWild%20Drive.mp3?alt=media&token=11cf10f1-93fe-4588-99d0-92293f8ace33",
//            "https://firebasestorage.googleapis.com/v0/b/kotlin-music-player.appspot.com/o/songs%2FYu-Gi-Oh_-_Shuffle_Karaoke.mp3?alt=media&token=872ef4e1-a310-4c96-9ef6-789ed1f65177",
//            "https://firebasestorage.googleapis.com/v0/b/kotlin-music-player.appspot.com/o/songs%2FOverlap.mp3?alt=media&token=8b602dfc-86f4-4324-8e26-981a8342148b",
//            "https://firebasestorage.googleapis.com/v0/b/kotlin-music-player.appspot.com/o/songs%2FYu-Gi-Oh%20-%20Voice.mp3?alt=media&token=08cad36d-b1b0-4853-a001-ffd8ea3224de",
//            "https://firebasestorage.googleapis.com/v0/b/kotlin-music-player.appspot.com/o/songs%2Fchangetheworld.mp3?alt=media&token=03379f90-0d81-4b8d-abdb-c659f134e3e9"
//        )
//        //Get
//        val urlSong = listaUrl.get(positionTrack)
//
//        // create a media player
//        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
//        mediaPlayer.setDataSource(urlSong)
//        mediaPlayer.prepareAsync()
//        mediaPlayer.setVolume(0.5f, 0.5f)
//
//        mediaPlayer.setOnPreparedListener(MediaPlayer.OnPreparedListener() {
//            _totalTime = mediaPlayer.duration
//            //Progress bar value paramater
//            positionBar.max = _totalTime
//            if (!mediaPlayer.isPlaying) {
//                mediaPlayer.start()
//                playBtn.setBackgroundResource(R.drawable.ic_pause_black_24dp)
//            }
//        })
//
//        mediaPlayer.setOnCompletionListener {
//            //UPDATE next song to play
//            if (_positionTrack < listaUrl.size - 1) {
//                //Go to the next song
//                _positionTrack++
//            } else {
//                //Back to the first song
//                _positionTrack = 0
//            }
//            mediaPlayer.reset()
//            initMusicPlayer(_positionTrack)
//        }
//    }
}