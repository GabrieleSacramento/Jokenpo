package br.com.gabrielesacramento.jokenpo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.gabrielesacramento.jokenpo.R.id.etPlayerOne
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity: AppCompatActivity() {

    var mNamePlayerOne: String = ""
    var mNamePlayerTwo: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        btnStart.setOnClickListener {
            goToGame()
        }

        btnAbout.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
    }

    fun goToGame() {

        if(verifyFillNames() == true) {

            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("PLAYERONE", mNamePlayerOne)
                putExtra("PLAYERTWO", mNamePlayerTwo)
            }

            startActivity(intent)
        }else{
            etPlayerOne.error = "Campo obrigatório"
            etPlayerTwo.error = "Campo obrigatório"
        }
    }

    fun verifyFillNames() : Boolean {

        mNamePlayerOne = etPlayerOne.text.toString()
        mNamePlayerTwo = etPlayerTwo.text.toString()

        if(mNamePlayerOne.isEmpty() == true || mNamePlayerTwo.isEmpty() == true) {
            return false
        }

        return true
    }
}