package com.example.armazenarinf
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TarefaAdapter(
    private val lista: List<Tarefa>
) : RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TarefaViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_tarefa,
                parent,
                false
            )

        return TarefaViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: TarefaViewHolder,
        position: Int
    ) {

        val tarefa = lista[position]

        holder.txtDescricao.text =
            tarefa.descricao

        holder.txtResponsavel.text =
            "Responsável: ${tarefa.responsavel}"

        holder.txtPorcentagem.text =
            "Conclusão: ${tarefa.porcentagem}%"
    }

    override fun getItemCount() = lista.size

    class TarefaViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val txtDescricao =
            view.findViewById<TextView>(R.id.txtDescricao)

        val txtResponsavel =
            view.findViewById<TextView>(R.id.txtResponsavel)

        val txtPorcentagem =
            view.findViewById<TextView>(R.id.txtPorcentagem)
    }
}