package com.projeto.pb_android_radion.fragment

import android.annotation.SuppressLint
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.projeto.pb_android_radion.R
import com.projeto.pb_android_radion.viewModel.MusicaViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.FirebaseStorage.getInstance
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.fragment_player.*

class PlayerFragment : Fragment() {

    private lateinit var musicaViewModel: MusicaViewModel
    private var _totalTime: Int = 0
    private var _positionTrack: Int = 0
    private val mediaPlayer = MediaPlayer()
    val firebaseStore = FirebaseFirestore.getInstance()
    // private var lista: MutableList<StorageReference> = mutableListOf()
    lateinit var storageReference: StorageReference
    lateinit var firebaseStorage: FirebaseStorage
    var listaUrl = listOf<String>(
        "https://firebasestorage.googleapis.com/v0/b/radion-23f62.appspot.com/o/Audio%2F01%20-%20Guns%20N'%20Roses%20-%20Welcome%20to%20the%20Jungle.mp3?alt=media&token=4202bda8-f238-4f43-baff-86b5ea638062",
        "https://firebasestorage.googleapis.com/v0/b/radion-23f62.appspot.com/o/Audio%2F(IT)%20Skank%20-%20Ainda%20Gosto%20Dela(1).mp3?alt=media&token=a64bf254-7022-437f-a33b-e1256883770d",
        "https://firebasestorage.googleapis.com/v0/b/radion-23f62.appspot.com/o/Audio%2F01%20-%20Guns%20N'%20Roses%20-%20Welcome%20to%20the%20Jungle.mp3?alt=media&token=4202bda8-f238-4f43-baff-86b5ea638062",
        "https://firebasestorage.googleapis.com/v0/b/radion-23f62.appspot.com/o/Audio%2F01%20-%20Guns%20N'%20Roses%20-%20Welcome%20to%20the%20Jungle.mp3?alt=media&token=4202bda8-f238-4f43-baff-86b5ea638062",
        "https://firebasestorage.googleapis.com/v0/b/radion-23f62.appspot.com/o/Audio%2F01%20-%20Guns%20N'%20Roses%20-%20Welcome%20to%20the%20Jungle.mp3?alt=media&token=4202bda8-f238-4f43-baff-86b5ea638062"
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            musicaViewModel = ViewModelProviders.of(it).get(MusicaViewModel::class.java)
        }

        var musicaNome = ""
        var musicaUrl = ""
        firebaseStorage = getInstance()
        storageReference = firebaseStorage.reference.child("Audio")
     /*   storageReference.listAll().addOnSuccessListener { listResult ->

            listResult.items.forEach { item ->
                //  lista.add(item)
                musicaNome = item.name
                musicaUrl = item.downloadUrl.toString()

                item.downloadUrl.addOnSuccessListener {
                    //  musicaUrl = item.downloadUrl.toString()
                     //musicaViewModel.musica?.nomeMusica = item.downloadUrl.toString()
                    //Log.i("user", it.downloadUrl.toString())
                }
                //  url(item.downloadUrl.toString())
                 infosMusica(musicaNome, musicaUrl)
            }
        }*/

           //POSICAO DA LISTA
        initMusicPlayer(_positionTrack)

        // PROGRESS BAR
        positionBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if (fromUser) {
                        mediaPlayer.seekTo(progress)
                    }
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                }
            }
        )
        fun createTimeLabel(time: Int): String {
            var timeLabel = ""
            var min = time / 1000 / 60
            var sec = time / 1000 % 60

            timeLabel = "$min:"
            if (sec < 10) timeLabel += "0"
            timeLabel += sec

            return timeLabel
        }

        @SuppressLint("HandlerLeak")
        var handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                var currentPosition = msg.what
                //Update positionBar
                positionBar.progress = currentPosition
                //Update Labels
                var elapsedTime = createTimeLabel(currentPosition)
                elapsedTimeLabel.text = elapsedTime

                var remainingTime = createTimeLabel(_totalTime - currentPosition)
                remainingTimeLabel.text = remainingTime
            }
        }

        // Thread
        Thread(Runnable {
            while (true) {
                try {
                    var msg = Message()
                    msg.what = mediaPlayer.currentPosition
                    handler.sendMessage(msg)
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                }
            }
        }).start()

        playBtn.setOnClickListener() {
            if (mediaPlayer.isPlaying) {
                //Stop
                mediaPlayer.pause()
                playBtn.setBackgroundResource(R.drawable.iconeplay)
            } else {
                //Start
                mediaPlayer.start()
                playBtn.setBackgroundResource(R.drawable.ic_pause_black_24dp)
            }
        }
    }

    private fun initMusicPlayer(positionTrack: Int) {

        //Get
        val urlSong = listaUrl.get(positionTrack)

        // create a media player
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayer.setDataSource(urlSong)
        mediaPlayer.prepareAsync()
        mediaPlayer.setVolume(0.5f, 0.5f)

        mediaPlayer.setOnPreparedListener(MediaPlayer.OnPreparedListener() {
            _totalTime = mediaPlayer.duration
            //Progress bar value paramater
            positionBar.max = _totalTime
            //ESSE IF INICA O PLAYER SOZINHO
           /* if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
                playBtn.setBackgroundResource(R.drawable.ic_pause_black_24dp)
            }*/
        })

        mediaPlayer.setOnCompletionListener {
            //UPDATE next song to play
            if (_positionTrack < listaUrl.size - 1) {
                //Go to the next song
                _positionTrack++
            } else {
                //Back to the first song
                _positionTrack = 0
            }
            mediaPlayer.reset()
            initMusicPlayer(_positionTrack)
        }
    }

    ///UPLOAD DAS INFOS NO STORE ---- NÂO APAGUE
   /* private fun infosMusica(musicaNome: String, musicaUrl: String)  {
        val infoMusica: MutableMap<String, Any> = HashMap()
        infoMusica["nomeMusica"] = musicaNome
        infoMusica["musica"] = musicaUrl
        infoMusica["musicaUrl"] = ""
        Log.i("info", infoMusica.toString())
        firebaseStore.collection(" listaMusicas").document()
        .set(infoMusica)
    }*/
}

