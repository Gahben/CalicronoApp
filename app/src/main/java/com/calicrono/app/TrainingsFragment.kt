package com.calicrono.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class TrainingsFragment : Fragment() {
    
    private lateinit var trainingTypeSelector: LinearLayout
    private lateinit var trainingDetailCard: MaterialCardView
    private lateinit var trainingTitle: TextView
    private lateinit var exercisesRecycler: RecyclerView
    
    private var currentTraining = 0
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trainings, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        trainingTypeSelector = view.findViewById(R.id.training_type_selector)
        trainingDetailCard = view.findViewById(R.id.training_detail_card)
        trainingTitle = view.findViewById(R.id.training_title)
        exercisesRecycler = view.findViewById(R.id.exercises_recycler)
        
        setupTrainingButtons()
        showTraining(0)
    }
    
    private fun setupTrainingButtons() {
        val trainings = listOf("PUSH", "PULL", "PERNAS", "FULL")
        trainings.forEachIndexed { index, name ->
            val button = MaterialButton(requireContext())
            button.text = name
            button.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            button.setPadding(32, 12, 32, 12)
            button.setOnClickListener { showTraining(index) }
            trainingTypeSelector.addView(button)
        }
    }
    
    private fun showTraining(index: Int) {
        currentTraining = index
        trainingDetailCard.visibility = View.VISIBLE
        
        val training = getTrainingData(index)
        trainingTitle.text = "${training.name} - ${training.title}"
        
        exercisesRecycler.layoutManager = LinearLayoutManager(requireContext())
        exercisesRecycler.adapter = ExerciseAdapter(training.exercises)
    }
    
    private fun getTrainingData(index: Int): TrainingData {
        return when(index) {
            0 -> TrainingData("PUSH", "Peito, Ombros, Tríceps", listOf(
                Exercise("Flexão convencional", "4 séries x 12-15 reps", "Base do peito"),
                Exercise("Pike push-up", "4 séries x 10-12 reps", "Ombros"),
                Exercise("Flexão diamante", "3 séries x 8-12 reps", "Tríceps"),
                Exercise("Flexão archer", "3 séries x 6-8 cada", "Força unilateral")
            ))
            1 -> TrainingData("PULL", "Costas, Bíceps, Trapézio", listOf(
                Exercise("Chin-up", "4 séries x 6-10 reps", "Bíceps e costas"),
                Exercise("Pull-up largo", "3 séries x 5-8 reps", "Dorsais"),
                Exercise("Remada invertida", "4 séries x 10-12 reps", "Costas média"),
                Exercise("Superman T/Y/W", "3 séries x 12 reps", "Lombar")
            ))
            2 -> TrainingData("PERNAS", "Quadríceps, Glúteos, Posterior", listOf(
                Exercise("Agachamento búlgaro", "4 séries x 8-10 cada", "Quadríceps"),
                Exercise("Pistol squat", "3 séries x 5-8 cada", "Força unilateral"),
                Exercise("Ponte glúteo", "4 séries x 12 reps", "Glúteos"),
                Exercise("Panturrilha", "4 séries x 15-20 reps", "Panturrilhas")
            ))
            else -> TrainingData("FULL", "Corpo Inteiro + Potência", listOf(
                Exercise("Barra explosiva", "4 séries x 5-7 reps", "Potência"),
                Exercise("Flexão com palma", "3 séries x 8-10 reps", "Explosão"),
                Exercise("Agachamento com salto", "3 séries x 10 reps", "Potência perna"),
                Exercise("Burpee", "3 séries x 10 reps", "Full body")
            ))
        }
    }
}

data class TrainingData(val name: String, val title: String, val exercises: List<Exercise>)
data class Exercise(val name: String, val sets: String, val note: String)

class ExerciseAdapter(private val exercises: List<Exercise>) : 
    RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise, parent, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(exercises[position])
    }
    
    override fun getItemCount() = exercises.size
    
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameText = itemView.findViewById<TextView>(R.id.exercise_name)
        private val setsText = itemView.findViewById<TextView>(R.id.exercise_sets)
        private val noteText = itemView.findViewById<TextView>(R.id.exercise_note)
        
        fun bind(exercise: Exercise) {
            nameText.text = exercise.name
            setsText.text = exercise.sets
            noteText.text = exercise.note
        }
    }
    }
