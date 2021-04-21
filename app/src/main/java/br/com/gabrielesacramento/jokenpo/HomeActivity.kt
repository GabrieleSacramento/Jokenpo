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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        btnStart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("PLAYERONE", etPlayerOne.text.toString())
                putExtra("PLAYERTWO", etPlayerTwo.text.toString())
            }

            startActivity(intent)
        }

        btnAbout.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
    }
}