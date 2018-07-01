package br.com.gean.enjoei.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import br.com.gean.enjoei.R
import br.com.gean.enjoei.arquitetura.Util
import br.com.gean.enjoei.ui.fragments.*
import kotlinx.android.synthetic.main.activity_principal.*

class PrincipalActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var abaSelecionada: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        imvHome.setOnClickListener(this@PrincipalActivity)
        imvSearch.setOnClickListener(this@PrincipalActivity)
        imvCamera.setOnClickListener(this@PrincipalActivity)
        imvMail.setOnClickListener(this@PrincipalActivity)
        imvUser.setOnClickListener(this@PrincipalActivity)

        imvHome.isSelected = false
        imvSearch.isSelected = false
        imvCamera.isSelected = false
        imvMail.isSelected = false
        imvUser.isSelected = false

        abaSelecionada = imvHome
        carregarFragment(R.id.imvHome)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            carregarFragment(v.id)
        }
    }

    private fun carregarFragment(idMenuItem: Int) {
        abaSelecionada.isSelected = false

        var fragmentClass: Class<*> = HomeFragment::class.java
        when (idMenuItem) {
            R.id.imvHome -> {
                fragmentClass = HomeFragment::class.java
                abaSelecionada = imvHome
            }
            R.id.imvSearch -> {
                fragmentClass = SearchFragment::class.java
                abaSelecionada = imvSearch
            }
            R.id.imvCamera -> {
                fragmentClass = CameraFragment::class.java
                abaSelecionada = imvCamera
            }
            R.id.imvMail -> {
                fragmentClass = MailFragment::class.java
                abaSelecionada = imvMail
            }
            R.id.imvUser -> {
                fragmentClass = UserFragment::class.java
                abaSelecionada = imvUser
            }
        }

        abaSelecionada.isSelected = true
        Util().substituirFragment(supportFragmentManager, fragmentClass, R.id.frlTelas)
    }

}

