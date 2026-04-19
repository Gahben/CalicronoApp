package com.calicrono.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class WeekFragment : Fragment() {
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var dayDetailCard: MaterialCardView
    private lateinit var dayDetailText: TextView
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_week, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        recyclerView = view.findViewById(R.id.days_recycler)
        dayDetailCard = view.findViewById(R.id.day_detail_card)
        dayDetailText = view.findViewById(R.id.day_detail_text)
        
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = WeekAdapter(getDaysData()) { day ->
            showDayDetail(day)
        }
    }
    
    private fun getDaysData(): List<DayData> = listOf(
        DayData("SEG", "PUSH", "#D85A30", "Peito, Ombros, Tríceps", 
            listOf("Flexão", "Pike push-up", "Flexão diamante", "Handstand")),
        DayData("TER", "PULL", "#185FA5", "Costas, Bíceps, Trapézio",
            listOf("Chin-up", "Pull-up", "Remada invertida", "L-sit")),
        DayData("QUA", "PERNAS", "#3B6D11", "Quadríceps, Glúteos, Posterior",
            listOf("Agachamento búlgaro", "Pistol squat", "Ponte glúteo", "Panturrilha")),
        DayData("QUI", "FULL", "#534AB7", "Corpo Inteiro + Potência",
            listOf("Barra explosiva", "Flexão com palma", "Agachamento com salto", "Burpee")),
        DayData("SEX", "DANÇA", "#0F6E56", "Samba, Breaking, Popping",
            listOf("Ginga", "Toprock", "6-step", "Isolamento")),
        DayData("SÁB", "SPRINT", "#854F0B", "4x20s Máximo",
            listOf("Sprint", "Pausa ativa", "Repete 4x"))
    )
    
    private fun showDayDetail(day: DayData) {
        dayDetailCard.visibility = View.VISIBLE
        val exercises = day.exercises.joinToString("\n• ") { it }
        dayDetailText.text = "${day.title}\n\n• $exercises"
    }
}

data class DayData(
    val label: String,
    val type: String,
    val color: String,
    val title: String,
    val exercises: List<String>
)

class WeekAdapter(
    private val days: List<DayData>,
    private val onDayClick: (DayData) -> Unit
) : RecyclerView.Adapter<WeekAdapter.ViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_day, parent, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(days[position], onDayClick)
    }
    
    override fun getItemCount() = days.size
    
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(day: DayData, onClick: (DayData) -> Unit) {
            itemView.setOnClickListener { onClick(day) }
        }
    }
}
