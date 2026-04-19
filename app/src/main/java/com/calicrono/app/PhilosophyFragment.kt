package com.calicrono.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PhilosophyFragment : Fragment() {
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_philosophy, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val recyclerView = view.findViewById<RecyclerView>(R.id.philosophy_recycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = PhilosophyAdapter(getPhilosophyData())
    }
    
    private fun getPhilosophyData(): List<PhilosophyItem> = listOf(
        PhilosophyItem(
            "Força sem mobilidade = prisão muscular",
            "Você não vai ficar rígido porque mobilidade não é opcional nesse programa - está embutida em todo treino. O aquecimento ativo, o bloco skill e o alongamento final somam 25 min por sessão de trabalho de amplitude. Ginastas são os atletas mais fortes e flexíveis ao mesmo tempo por esse motivo."
        ),
        PhilosophyItem(
            "Sprint 4×20s - o que acontece no corpo",
            "Sprint máximo ativa fibras musculares de contração rápida que treino de força não alcança completamente. Libera GH (hormônio do crescimento) de pico. Aumenta testosterona. Melhora velocidade máxima e agilidade de reação. 4×20s com 2-3 min de descanso entre é suficiente - mais que isso é contraproducente sem recuperação adequada."
        ),
        PhilosophyItem(
            "Dança = treino do sistema nervoso",
            "Força treina músculo. Dança treina o sistema nervoso - a capacidade de comandar o músculo com precisão, ritmo e fluidez. Um atleta que dança tem vantagem em agilidade, tempo de reação e consciência corporal que quem só levanta peso não tem."
        ),
        PhilosophyItem(
            "Equilíbrio e harmonia corporal",
            "Pernas recebem volume equivalente ao tronco. Mobilidade de quadril e coluna trabalha junto com força. Dança exige simetria e coordenação bilateral. O resultado é um corpo que funciona como sistema integrado, não como partes isoladas."
        ),
        PhilosophyItem(
            "Progressão honesta",
            "Mês 1-2: consolida a forma de todos os movimentos. Mês 3-5: hipertrofia visível, primeiros progressos em skill. Mês 6-10: força real, handstand livre, pistol squat, dança com controle. Mês 12+: físico próximo das referências, atleta funcional completo."
        ),
        PhilosophyItem(
            "O que não negociar",
            "Sono de 8h. Proteína em toda refeição. Bloco de mobilidade ao final de cada treino. Descanso real nos dias de descanso. Consistência de meses, não de semanas."
        )
    )
}

data class PhilosophyItem(val title: String, val content: String)

class PhilosophyAdapter(private val items: List<PhilosophyItem>) : 
    RecyclerView.Adapter<PhilosophyAdapter.ViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_philosophy, parent, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
    
    override fun getItemCount() = items.size
    
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleText = itemView.findViewById<TextView>(R.id.philosophy_title)
        private val contentText = itemView.findViewById<TextView>(R.id.philosophy_content)
        
        fun bind(item: PhilosophyItem) {
            titleText.text = item.title
            contentText.text = item.content
        }
    }
    }
