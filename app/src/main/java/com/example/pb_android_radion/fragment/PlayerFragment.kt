package com.example.pb_android_radion.fragment

import android.annotation.SuppressLint
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.pb_android_radion.R
import com.example.pb_android_radion.viewModel.MusicaViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.FirebaseStorage.getInstance
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.fragment_player.*


class PlayerFragment : Fragment() {

    private lateinit var musicaViewModel: MusicaViewModel

    private var _totalTime: Int = 0
//    private val mediaPlayer = MediaPlayer()
    val firebaseStore = FirebaseFirestore.getInstance()

    lateinit var storageReference: StorageReference
    lateinit var firebaseStorage: FirebaseStorage


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    //@SuppressLint("SdCardPath")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            musicaViewModel = ViewModelProviders.of(it).get(MusicaViewModel::class.java)
        }

        firebaseStorage = getInstance()
        storageReference = firebaseStorage.reference.child("gs://radion-23f62.appspot.com/Audio")

        // lista dentro da pasta audio
        var listaItemsPastaAudio = storageReference.listAll()

        listaItemsPastaAudio.addOnSuccessListener {
            // foreach dentro da lista de musicas
            it.items.forEach {

            }
        }

      /*   val teste2 = storageReference.downloadUrl
        teste2.addOnSuccessListener {
            for (i in listOf(it)) {
                Log.i("msg", "${i}")
            }
        }*/
    /*    val listaTeste = mutableListOf<StorageReference>()
        val testeRef = firebaseStorage.reference.child("Audio").listAll()
            .addOnSuccessListener { testeRef ->
                testeRef.prefixes.forEach { prefix ->

                    listaTeste.add(prefix)
                    Log.i("teste2", "${listaTeste}")

                }

            }
        Log.i("teste2", "${listaTeste}")*/
        /*testeRef.listAll().addOnSuccessListener {

            testeRef -> testeRef.prefixes.forEach { prefix ->

            listaTeste.add(prefix)

            Log.i("teste", {listaTeste})
        }
            testeRef.items.forEach { item ->
                Log.i("MSG", {testeRef}.toString())
            }
        }.addOnFailureListener{
            //Erro ocorre
        }
*/

        //Referencia - Diretorio Raiz
        //storageReference = firebaseStorage.reference
    //   storageReference = firebaseStorage.getReferenceFromUrl("gs://radion-23f62.appspot.com/Audio")
      // storageReference = firebaseStorage.reference("Audio")

     /*   val teste = storageReference.listAll().addOnSuccessListener {
            it.items.forEach {
                it.metadata.addOnSuccessListener {
                    it.contentType
                    Log.i("teste6", "${it}")
                }
            }
        }*/





        /*    val arquivoMusica = storageReference.child("Audio/John Newman - Love Me Again.mp3")      // C:/js.png

          // val task = arquivoMusica.stream
          // val localFile = File.createTempFile("audio", "mp3")
          val task = arquivoMusica.getFile(File(context?.filesDir, "musicas"))

          task.addOnSuccessListener {
              Toast.makeText(
                  activity?.baseContext,
                  "***** \\o/ ******",
                  Toast.LENGTH_LONG
              ).show()
          }.addOnFailureListener {
              Toast.makeText(
                  activity?.baseContext,
                  it.message,
                  Toast.LENGTH_LONG
              ).show()
          }

  */
        var mediaPlayer = MediaPlayer.create(requireContext().applicationContext, R.raw.wilddrive)//onde passa a música
        mediaPlayer.isLooping = true

        //val myUri: Uri = arquivoMusica// initialize Uri here
        /* val mediaPlayer: MediaPlayer? = MediaPlayer().apply {
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            setDataSource( requireContext().applicationContext,
                musica,
                null
            )
//            prepare()
            start()
        }*/
//        val teste6 = listOf(storageReference.child("Audio").downloadUrl)
//        teste6.forEach {
//            Log.i("oioioi", "$it")
//        }
//
//        val musica = Uri.parse("teste.toString()")// passar a urml da musica
//        try {
//
//            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
//            mediaPlayer.setDataSource(
//                requireContext().applicationContext,
//                musica,
//                null
//            )
//            mediaPlayer.prepareAsync()
//            mediaPlayer.start()
//
//        } catch (e: Exception) {
//            Toast.makeText(
//                requireContext().applicationContext,
//                "Deu ruim",
//                Toast.LENGTH_LONG
//            ).show()
//        }

        playBtn.setBackgroundResource(R.drawable.iconeplay)

        _totalTime = mediaPlayer.duration
        playBtn.setOnClickListener {
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


        // Position Bar
        positionBar.max = _totalTime
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
            while (mediaPlayer != null) {
                try {
                    var msg = Message()
                    msg.what = mediaPlayer.currentPosition
                    handler.sendMessage(msg)
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                }
            }
        }).start()


        // val task = arquivoMusica.getFile(File(filesDir, "js.png"))
        // data/data/pacote/files

        /*  task.addOnSuccessListener {
            //if (it != null)
            Toast.makeText(
                this,
                "***** \\o/ ******",
                Toast.LENGTH_LONG
            ).show()
        }.addOnFailureListener {
            Toast.makeText(
                this,
                it.message,
                Toast.LENGTH_LONG
            ).show()
        }*/
    }


}

// Adiciona a quantidade de estrelas na classificação da musica de acordo com o nome da musica
        /*if(txtVwNomeMusica.text == musicaViewModel.musica!!.nomeMusica){
            musicaViewModel.musica!!.classificacao = ratingClassificacao.numStars
        }*/


    // Método para que o componente de classificação recebe o número de estrelas de acordo
    // com o nome da musica
    /*private fun verificarClassificacao(){
        if(txtVwNomeMusica.text == musicaViewModel.musica!!.nomeMusica){
            ratingClassificacao.numStars = musicaViewModel.musica!!.classificacao
        }
    }*/

