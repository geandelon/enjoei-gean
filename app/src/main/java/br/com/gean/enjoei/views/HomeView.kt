package br.com.gean.enjoei.views

import br.com.gean.enjoei.modelos.Product

interface HomeView {

    fun exibirProdutos(produtos: List<Product>)

    fun exibirCarregando(exibir: Boolean)

    fun exibirErro(msg: String)

}