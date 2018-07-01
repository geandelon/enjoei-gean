package br.com.gean.enjoei.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.gean.enjoei.R
import kotlinx.android.synthetic.main.fragment_erro.view.*

class ErroFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.fragment_erro, container, false);

        view.btnTentarNovamente.setOnClickListener { tentarNovamente() }

        return view
    }

    fun tentarNovamente() {
        activity?.onBackPressed()
    }

}