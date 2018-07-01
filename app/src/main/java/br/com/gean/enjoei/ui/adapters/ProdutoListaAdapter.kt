package br.com.gean.enjoei.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.gean.enjoei.R
import br.com.gean.enjoei.arquitetura.Util
import br.com.gean.enjoei.modelos.Product
import br.com.gean.enjoei.utils.Constantes
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_produto.view.*

class ProdutoListaAdapter(val lista: List<Product>, val onClick: (Product) -> Unit) : RecyclerView.Adapter<ProdutoListaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_produto, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = lista[position]
        holder.bindView(produto, onClick)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(produto: Product, onClick: (Product) -> Unit) {
            with(itemView) {
                txvDescricao.text = produto.title
                txvPreco.text = "R$ ${"%.1f".format(produto.price)}"
                if (produto.discount_percentage > 0) {
                    txvDesconto.visibility = TextView.VISIBLE
                    txvDesconto.text = "-${produto.discount_percentage}%"
                } else {
                    txvDesconto.visibility = TextView.GONE
                }
                if (produto.likes_count > 0) {
                    txvQtdLikes.text = produto.likes_count.toString()
                } else {
                    txvQtdLikes.text = ""
                }

                Glide.with(itemView.context)
                        .load(Constantes.URL_FOTO + produto.photos[0].public_id)
                        .apply(Util().getRequestPadrao())
                        .into(imvFotoProduto)

                Glide.with(itemView.context)
                        .load(Constantes.URL_FOTO + produto.user.avatar.public_id)
                        .apply(Util().getRequestPadrao())
                        .into(imvFotoDono)

                setOnClickListener { onClick(produto) }
            }
        }

    }
}

