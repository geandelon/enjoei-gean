package br.com.gean.enjoei.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import br.com.gean.enjoei.R
import br.com.gean.enjoei.utils.Constantes

class SplashActivity : AppCompatActivity(), Runnable {

    private var redirecionar: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        agendarRedirecionamento()
    }

    fun agendarRedirecionamento() {
        Handler().apply {
            postDelayed(this@SplashActivity, Constantes.DELAY_SPLASH_SCREEN)
        }
        redirecionar = true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        redirecionar = false
    }

    override fun run() {
        startActivity(Intent(this, PrincipalActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        })
        finish()
    }


}