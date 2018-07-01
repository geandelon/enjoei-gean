package br.com.gean.enjoei.presenter

import br.com.gean.enjoei.arquitetura.ApiService
import br.com.gean.enjoei.arquitetura.mvp.MvpPresenter
import br.com.gean.enjoei.modelos.ProdutoEnjoei
import br.com.gean.enjoei.views.HomeView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class HomePresenter : MvpPresenter<HomeView>() {

    fun consultarProdutos() {
        view?.exibirCarregando(true)

        val call = ApiService().service.consultarProdutos()
        call.enqueue(object : Callback<ProdutoEnjoei> {
            override fun onFailure(call: Call<ProdutoEnjoei>?, t: Throwable?) {
                view?.exibirCarregando(false)
                view?.exibirErro("Não foi possível carregar os produtos")
                Timber.e("Erro")
            }

            override fun onResponse(call: Call<ProdutoEnjoei>, response: Response<ProdutoEnjoei>?) {
                Timber.d("Sucesso")
                response?.body()?.let {
                    view?.exibirCarregando(false)
                    view?.exibirProdutos(it.products)
                }
            }
        })
    }

}