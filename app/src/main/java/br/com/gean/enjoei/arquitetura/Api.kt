package br.com.gean.enjoei.arquitetura

import br.com.gean.enjoei.modelos.ProdutoEnjoei
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("products/home")
    fun consultarProdutos(): Call<ProdutoEnjoei>

}