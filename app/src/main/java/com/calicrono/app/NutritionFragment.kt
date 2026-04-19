package com.calicrono.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NutritionFragment : Fragment() {
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nutrition, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val recyclerView = view.findViewById<RecyclerView>(R.id.nutrition_recycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = NutritionAdapter(getNutritionData())
        
        // Stats
        val stats = listOf(
            Triple("3.1k", "kcal/dia", "#D85A30"),
            Triple("150g", "proteína", "#185FA5"),
            Triple("380g", "carboidrato", "#3B6D11"),
            Triple("8h", "sono mínimo", "#534AB7")
        )
        val statsContainer = view.findViewById<LinearLayout>(R.id.stats_container)
        stats.forEach { (value, label, color) ->
            val statView = layoutInflater.inflate(R.layout.item_stat, statsContainer, false)
            statView.findViewById<TextView>(R.id.stat_value).text = value
            statView.findViewById<TextView>(R.id.stat_label).text = label
            statsContainer.addView(statView)
        }
    }
    
    private fun getNutritionData(): List<Meal> = listOf(
        Meal("07h - Acordar", "Base energética", listOf(
            "3-4 ovos (mexidos ou cozidos)",
            "2 fatias pão ou tapioca",
            "1 banana grande",
            "café sem açúcar"
        ), "~550 kcal", "~28g prot"),
        Meal("10h - Lanche", "Manutenção anabólica", listOf(
            "2 ovos cozidos",
            "1 fruta (banana preferida)",
            "1 col. sopa de amendoim"
        ), "~300 kcal", "~16g prot"),
        Meal("12h-13h - Almoço", "Refeição principal", listOf(
            "Arroz (4 colheres grandes)",
            "Feijão (2 conchas)",
            "Frango/sardinha/ovo (150g)",
            "Legume ou folha à vontade"
        ), "~850 kcal", "~50g prot"),
        Meal("16h - Pré-treino", "Energia e foco", listOf(
            "1 banana + amendoim",
            "ou pão com ovo",
            "água - 500ml antes do treino"
        ), "~280 kcal", "~12g prot"),
        Meal("19h-20h - Pós-treino", "Recuperação e crescimento", listOf(
            "Arroz + feijão",
            "3 ovos ou sardinha",
            "qualquer vegetal disponível"
        ), "~750 kcal", "~38g prot"),
        Meal("22h - Pré-sono", "Síntese noturna", listOf(
            "2-3 ovos cozidos",
            "ou iogurte natural se possível",
            "sem carboidrato pesado"
        ), "~200 kcal", "~18g prot")
    )
}

data class Meal(val time: String, val name: String, val items: List<String>, val kcal: String, val protein: String)

class NutritionAdapter(private val meals: List<Meal>) : 
    RecyclerView.Adapter<NutritionAdapter.ViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meal, parent, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(meals[position])
    }
    
    override fun getItemCount() = meals.size
    
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val timeText = itemView.findViewById<TextView>(R.id.meal_time)
        private val nameText = itemView.findViewById<TextView>(R.id.meal_name)
        private val itemsContainer = itemView.findViewById<LinearLayout>(R.id.meal_items)
        private val kcalText = itemView.findViewById<TextView>(R.id.meal_kcal)
        private val proteinText = itemView.findViewById<TextView>(R.id.meal_protein)
        
        fun bind(meal: Meal) {
            timeText.text = meal.time
            nameText.text = meal.name
            kcalText.text = meal.kcal
            proteinText.text = meal.protein
            
            itemsContainer.removeAllViews()
            meal.items.forEach { item ->
                val itemView = LayoutInflater.from(itemView.context)
                    .inflate(R.layout.item_meal_item, itemsContainer, false)
                itemView.findViewById<TextView>(R.id.item_text).text = "• $item"
                itemsContainer.addView(itemView)
            }
        }
    }
    }
