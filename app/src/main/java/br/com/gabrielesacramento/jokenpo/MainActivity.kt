package br.com.gabrielesacramento.jokenpo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var playOneBet: Int = -1
    var playTwoBet: Int = -1
    var playerOneTotal: Int = 0
    var playerTwoTotal: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
            btnPlay.isEnabled = true
            hide()
        }

        imgBtnPaperLeft.setOnClickListener {
            playOneBet = 1
            btnPlay.isEnabled = true
            hide()
        }

        imgBtnScissorsLeft.setOnClickListener {
            playOneBet = 2
            btnPlay.isEnabled = true
            hide()
        }

        imgBtnRockRigth.setOnClickListener {
            playTwoBet = 0
            btnPlay.isEnabled = true
            hide()
        }

        imgBtnPaperRigth.setOnClickListener {
            playTwoBet = 1
            btnPlay.isEnabled = true
            hide()
        }

        imgBtnScissorsRigth.setOnClickListener {
            playTwoBet = 2
            btnPlay.isEnabled = true
            hide()
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
        }

        imgBtnLogo.setOnClickListener {
            reset()
        }
    }

    fun reset() {

        playerOneTotal = 0
        playerTwoTotal = 0

        pointResultOne.text = "Player one: $playerOneTotal ponto(s)"
        pointResultTwo.text = "Player Two: $playerTwoTotal ponto(s)"

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

        pointResultOne.text = "Player one: $playerOneTotal ponto(s)"
        pointResultTwo.text = "Player Two: $playerTwoTotal ponto(s)"

        return end
    }
}