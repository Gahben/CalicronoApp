package com.calicrono.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MobilityFragment : Fragment() {
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mobility, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val recyclerView = view.findViewById<RecyclerView>(R.id.mobility_recycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = MobilityAdapter(getMobilityData())
    }
    
    private fun getMobilityData(): List<MobilityCategory> = listOf(
        MobilityCategory("Aquecimento Ativo", "8-10 min antes do treino", listOf(
            MobilityItem("Círculos de quadril", "10 cada direção", "Libera articulação coxofemoral"),
            MobilityItem("World's greatest stretch", "5 cada lado", "Mobilidade total"),
            MobilityItem("Rotação torácica", "10 cada lado", "Coluna torácica"),
            MobilityItem("Agachamento profundo", "10 reps", "Abre tornozelo e quadril")
        )),
        MobilityCategory("Skill / Ginástica", "8-10 min após força", listOf(
            MobilityItem("Hollow body hold", "3x20-45s", "Base da ginástica"),
            MobilityItem("L-sit progressão", "3x10-20s", "Core e flexores"),
            MobilityItem("Handstand na parede", "3x20-30s", "Equilíbrio e ombros"),
            MobilityItem("Ponte (back bridge)", "3x20-30s", "Mobilidade espinhal")
        )),
        MobilityCategory("Alongamento Final", "8 min", listOf(
            MobilityItem("Pigeon pose", "60s cada", "Quadril externo"),
            MobilityItem("Fenda profunda", "45s cada", "Flexor de quadril"),
            MobilityItem("Isquiotibial", "45s cada", "Cadeia posterior"),
            MobilityItem("PNF peitoral", "3x cada", "Método de flexibilidade")
        ))
    )
}

data class MobilityCategory(val title: String, val duration: String, val items: List<MobilityItem>)
data class MobilityItem(val name: String, val duration: String, val note: String)

class MobilityAdapter(private val categories: List<MobilityCategory>) : 
    RecyclerView.Adapter<MobilityAdapter.ViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mobility_category, parent, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }
    
    override fun getItemCount() = categories.size
    
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleText = itemView.findViewById<TextView>(R.id.category_title)
        private val durationText = itemView.findViewById<TextView>(R.id.category_duration)
        private val itemsContainer = itemView.findViewById<LinearLayout>(R.id.items_container)
        
        fun bind(category: MobilityCategory) {
            titleText.text = category.title
            durationText.text = category.duration
            itemsContainer.removeAllViews()
            
            category.items.forEach { item ->
                val itemView = LayoutInflater.from(itemView.context)
                    .inflate(R.layout.item_mobility_exercise, itemsContainer, false)
                itemView.findViewById<TextView>(R.id.exercise_name).text = item.name
                itemView.findViewById<TextView>(R.id.exercise_duration).text = item.duration
                itemView.findViewById<TextView>(R.id.exercise_note).text = item.note
                itemsContainer.addView(itemView)
            }
        }
    }
    }
