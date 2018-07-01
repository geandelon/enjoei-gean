package br.com.gean.enjoei.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import br.com.gean.enjoei.R
import br.com.gean.enjoei.arquitetura.Util
import br.com.gean.enjoei.arquitetura.mvp.MvpFragment
import br.com.gean.enjoei.modelos.Product
import br.com.gean.enjoei.presenter.HomePresenter
import br.com.gean.enjoei.ui.activities.DetalheProdutoActivity
import br.com.gean.enjoei.ui.adapters.ProdutoListaAdapter
import br.com.gean.enjoei.ui.componentes.GridSpacingItemDecoration
import br.com.gean.enjoei.utils.Constantes
import br.com.gean.enjoei.views.HomeView
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.jetbrains.anko.longToast


class HomeFragment : MvpFragment<HomeView, HomePresenter>(), HomeView {
    lateinit var rcvDados: RecyclerView
    lateinit var pgbCarregando: ProgressBar

    override fun obterClassePresenter(): Class<HomePresenter> {
        return HomePresenter::class.java
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_home, container, false);

        rcvDados = view.rcvDados
        pgbCarregando = view.pgbCarregando

        val llm = GridLayoutManager(activity, 2)
        with(rcvDados) {
            layoutManager = llm
            setHasFixedSize(true)
            val spacingInPixels = resources.getDimensionPixelSize(R.dimen.item_margin_2)
            addItemDecoration(GridSpacingItemDecoration(2, spacingInPixels, true, 0))
        }

        (presenter as HomePresenter).consultarProdutos()

        return view;
    }

    fun onClickPDV(produto: Product) {
        val intent = Intent(activity, DetalheProdutoActivity::class.java)
        intent.putExtra(Constantes.EXTRA_PRODUTO, produto)
        activity?.startActivity(intent)
    }

    override fun exibirCarregando(exibir: Boolean) {
        if (exibir) {
            pgbCarregando.visibility = ProgressBar.VISIBLE
        } else {
            pgbCarregando.visibility = ProgressBar.GONE
        }

    }

    override fun exibirErro(msg: String) {
        activity?.longToast(msg)
        Util().substituirFragment(activity?.supportFragmentManager, ErroFragment::class.java, R.id.frlTelas)
    }

    override fun exibirProdutos(produtos: List<Product>) {
        rcvDados.adapter = ProdutoListaAdapter(produtos) { produto: Product -> onClickPDV(produto) }
        rcvDados.adapter.notifyDataSetChanged()
    }

}