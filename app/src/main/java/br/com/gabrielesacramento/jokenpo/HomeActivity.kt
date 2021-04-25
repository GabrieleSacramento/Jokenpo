package br.com.gabrielesacramento.jokenpo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity: AppCompatActivity() {

    private var mNamePlayerOne: String = ""
    private var mNamePlayerTwo: String = ""

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

    private fun goToGame() {



        if(verifyFillNames()) {

            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("PLAYERONE", mNamePlayerOne)
                putExtra("PLAYERTWO", mNamePlayerTwo)
            }

            startActivity(intent)
        }else{

            etPlayerOne.error = if(mNamePlayerOne.isEmpty()) getString(R.string.fill_required) else null
            etPlayerTwo.error = if(mNamePlayerTwo.isEmpty()) getString(R.string.fill_required) else null
        }
    }

    private fun verifyFillNames() : Boolean {

        mNamePlayerOne = etPlayerOne.text.toString()
        mNamePlayerTwo = etPlayerTwo.text.toString()

        if(mNamePlayerOne.isEmpty() || mNamePlayerTwo.isEmpty()) {
            return false
        }

        return true
    }
}