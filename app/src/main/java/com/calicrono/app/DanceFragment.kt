package com.calicrono.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DanceFragment : Fragment() {
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dance, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val recyclerView = view.findViewById<RecyclerView>(R.id.dance_recycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = DanceAdapter(getDanceData())
    }
    
    private fun getDanceData(): List<DanceCategory> = listOf(
        DanceCategory("Samba no Pé / Pagode", "Molejo de quadril, dissociação, ritmo", listOf(
            DanceItem("Ginga básica do samba", "10-15 min", "Quadril lidera o movimento"),
            DanceItem("Isolamento de quadril", "5 min", "Quadril sem mover tronco"),
            DanceItem("Miudinho", "5 min", "Passinhos rápidos, agilidade")
        )),
        DanceCategory("Breaking (b-boy) básico", "Força, explosão, controle", listOf(
            DanceItem("Toprock", "10 min", "Passos em pé com ritmo"),
            DanceItem("6-step (footwork)", "10 min", "Trabalho de pés no chão"),
            DanceItem("Baby freeze", "5 min", "Equilíbrio e core")
        )),
        DanceCategory("Popping / Isolation", "Controle neuromuscular", listOf(
            DanceItem("Hit básico", "10 min", "Contração e relaxamento"),
            DanceItem("Waving", "10 min", "Onda pelos braços"),
            DanceItem("Chest pop", "5 min", "Isolamento do peito")
        )),
        DanceCategory("Sessão Completa", "~50 min total", listOf(
            DanceItem("Aquecimento com ritmo", "5 min", "Ginga livre"),
            DanceItem("Samba + isolamento", "15 min", "Foco no molejo"),
            DanceItem("Breaking toprock", "15 min", "Força e ritmo"),
            DanceItem("Popping freestyle", "10 min", "Deixa fluir"),
            DanceItem("Mobilidade no ritmo", "5 min", "Encerramento")
        ))
    )
}

data class DanceCategory(val title: String, val focus: String, val items: List<DanceItem>)
data class DanceItem(val name: String, val duration: String, val note: String)

class DanceAdapter(private val categories: List<DanceCategory>) : 
    RecyclerView.Adapter<DanceAdapter.ViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dance_category, parent, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }
    
    override fun getItemCount() = categories.size
    
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleText = itemView.findViewById<TextView>(R.id.category_title)
        private val focusText = itemView.findViewById<TextView>(R.id.category_focus)
        private val itemsContainer = itemView.findViewById<LinearLayout>(R.id.items_container)
        
        fun bind(category: DanceCategory) {
            titleText.text = category.title
            focusText.text = category.focus
            itemsContainer.removeAllViews()
            
            category.items.forEach { item ->
                val itemView = LayoutInflater.from(itemView.context)
                    .inflate(R.layout.item_dance_exercise, itemsContainer, false)
                itemView.findViewById<TextView>(R.id.exercise_name).text = item.name
                itemView.findViewById<TextView>(R.id.exercise_duration).text = item.duration
                itemView.findViewById<TextView>(R.id.exercise_note).text = item.note
                itemsContainer.addView(itemView)
            }
        }
    }
}
