package br.com.gean.enjoei.arquitetura.mvp

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel

abstract class MvpPresenter<V> : ViewModel(), LifecycleObserver {

    protected var view: V? = null
        private set

    fun attachView(view: V) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }

    protected val isViewAttached: Boolean
        get() = view != null


    fun attachLifecycle(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }

    fun detachLifecycle(lifecycle: Lifecycle) {
        lifecycle.removeObserver(this)
    }

    fun onPresenterDestroy() {

    }
}