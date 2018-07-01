package br.com.gean.enjoei.arquitetura.mvp

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

abstract class MvpFragment<V, P : MvpPresenter<V>> : Fragment(), LifecycleOwner {

    lateinit var presenter: MvpPresenter<V>

    abstract fun obterClassePresenter(): Class<P>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        presenter = ViewModelProviders.of(this).get(obterClassePresenter())
        presenter.attachLifecycle(lifecycle)
        presenter.attachView(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachLifecycle(lifecycle)
        presenter.detachView()
    }

}