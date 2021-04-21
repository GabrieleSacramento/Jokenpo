package br.com.gabrielesacramento.jokenpo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var playOneBet: Int = -1
    var playTwoBet: Int = -1
    var playerOneTotal: Int = 0
    var playerTwoTotal: Int = 0
    var playerNameOne: String = "PLAYERONE"
    var playerNameTwo: String = "PLAYERTWO"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get the Intent that started this activity extract the string
        playerNameOne = intent.getStringExtra("PLAYERONE").toString()
        playerNameTwo = intent.getStringExtra("PLAYERTWO").toString()

        //Atualizar nome dos jogadores
        updatePlayerName()

        //Desabilitar o botão jogar
        btnPlay.isEnabled = false
        
        //Esconder os itens da aposta
        hide()

        // 0 pedra / rock
        // 1 papel / paper
        // 2 tesoura / scissors

        //botões de escolha do jogo
        imgBtnRockLeft.setOnClickListener {
            playOneBet = 0
            hide()
            lightButton()
        }

        imgBtnPaperLeft.setOnClickListener {
            playOneBet = 1
            hide()
            lightButton()
        }

        imgBtnScissorsLeft.setOnClickListener {
            playOneBet = 2
            hide()
            lightButton()
        }

        imgBtnRockRigth.setOnClickListener {
            playTwoBet = 0
            hide()
            lightButton()
        }

        imgBtnPaperRigth.setOnClickListener {
            playTwoBet = 1
            hide()
            lightButton()
        }

        imgBtnScissorsRigth.setOnClickListener {
            playTwoBet = 2
            hide()
            lightButton()
        }

        btnPlay.setOnClickListener {

            if (playOneBet != -1 || playTwoBet != -1) {

                tvResult.text = play()

                imgLeftBet.visibility = View.VISIBLE
                imgRightBet.visibility = View.VISIBLE
                tvResult.visibility = View.VISIBLE

                btnPlay.isEnabled = false
            }else{
                hide()
            }

            btnReset.setOnClickListener {
                if (playOneBet != -1 || playTwoBet != -1) {

                    tvResult.text = play()

                    imgLeftBet.visibility = View.VISIBLE
                    imgRightBet.visibility = View.VISIBLE
                    tvResult.visibility = View.VISIBLE

                    btnReset.isEnabled = false
                }else{
                    hide()
                }

                reset()
            }
        }
    }

    fun updatePlayerName() {

        pointResultOne.text = "$playerNameOne: $playerOneTotal ponto(s)"
        pointResultTwo.text = "$playerNameTwo: $playerTwoTotal ponto(s)"
    }

    fun lightButton() {

        //Acender botão
        btnPlay.isEnabled = playOneBet != -1 && playTwoBet != -1
    }

    fun reset() {

        //Resetar jogo
        playerOneTotal = 0
        playerTwoTotal = 0

        updatePlayerName()
        hide()
    }

    fun hide() {

        //Esconder os itens da aposta
        imgLeftBet.visibility = View.INVISIBLE
        imgRightBet.visibility = View.INVISIBLE
        tvResult.visibility = View.INVISIBLE
    }

    fun play(): String {

        var end: String = ""

        Log.d("Jogo comp: ", playOneBet.toString())
        Log.d("Game comp: ", playTwoBet.toString())

        when(playTwoBet){
            0 -> imgRightBet.setImageDrawable(getDrawable(R.drawable.rock_right))
            1 -> imgRightBet.setImageDrawable(getDrawable(R.drawable.paper_right))
            2 -> imgRightBet.setImageDrawable(getDrawable(R.drawable.scissors_right))
        }

        when(playOneBet){
            0 -> imgLeftBet.setImageDrawable(getDrawable(R.drawable.rock_left))
            1 -> imgLeftBet.setImageDrawable(getDrawable(R.drawable.paper_left))
            2 -> imgLeftBet.setImageDrawable(getDrawable(R.drawable.scissors_left))
        }

        // 0 pedra / rock
        // 1 é papel / paper
        // 2 é tesoura / scissors

        if(playTwoBet == playOneBet){
            end = "EMPATOU"
        }else if(playTwoBet == 1 && playOneBet == 0){
            end = "PERDEU"
            playerTwoTotal++
        }else if (playTwoBet == 0 && playOneBet == 2) {
            end = "PERDEU"
            playerTwoTotal++
        }else if (playTwoBet == 2 && playOneBet == 1) {
            end = "PERDEU"
            playerTwoTotal++
        }else{
            end = "GANHOU"
            playerOneTotal++
        }

        restart()
        updatePlayerName()

        return end
    }

    fun restart() {

        playOneBet = -1
        playTwoBet = -1
    }
}