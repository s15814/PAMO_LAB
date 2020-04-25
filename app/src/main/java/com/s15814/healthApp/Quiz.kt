package com.s15814.healthApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import java.util.*

class Quiz : Fragment() {
    private val quizQuestionList: MutableList<QuizQuestion> = ArrayList()
    private var questionText: TextView? = null
    private var firstAnswer: RadioButton? = null
    private var secondAnswer: RadioButton? = null
    private var answersGroup: RadioGroup? = null
    private var nextButton: Button? = null
    private var selectedButton = 0
    private var currentQuestion = 0
    private var correctAnswers = 0
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        return inflater.inflate(R.layout.quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quizQuestionList.add(QuizQuestion("Should you wash your hand often to prevent coronavirus?", "Yes", "No", 0))
        quizQuestionList.add(QuizQuestion("Should you self-isolate to stay safe?", "Yes", "No", 0))
        quizQuestionList.add(QuizQuestion("Where did coronavirus originate from?", "China", "Poland", 0))
        questionText = view.findViewById<View>(R.id.questionText) as TextView
        answersGroup = view.findViewById<View>(R.id.answerRadioGroup) as RadioGroup
        firstAnswer = view.findViewById<View>(R.id.firstAnswerButton) as RadioButton
        secondAnswer = view.findViewById<View>(R.id.secondAnswerButton) as RadioButton
        nextButton = view.findViewById<View>(R.id.quizNextQuestionButton) as Button
        answersGroup!!.clearCheck()
        nextQuestion()
        nextButton!!.setOnClickListener {
            if (currentQuestion < quizQuestionList.size) {
                checkAnswer()
                nextQuestion()
            } else {
                val quizOverText = "Quiz over."
                questionText!!.text = quizOverText
            }
        }
        view.findViewById<View>(R.id.quizBackButton).setOnClickListener {
            NavHostFragment.findNavController(this@Quiz)
                    .navigate(R.id.action_quiz_to_mainMenu)
        }
    }

    private fun nextQuestion() {
        answersGroup!!.clearCheck()
        if (currentQuestion < quizQuestionList.size) {
            questionText!!.text = quizQuestionList[currentQuestion].question
            firstAnswer!!.text = quizQuestionList[currentQuestion].answer1
            secondAnswer!!.text = quizQuestionList[currentQuestion].answer2
        } else {
            val resultText = "Quiz finished, you scored $correctAnswers/3 points."
            questionText!!.text = resultText
        }
        answersGroup!!.clearCheck()
    }

    private fun checkAnswer() {
        selectedButton = answersGroup!!.indexOfChild(
                view!!.findViewById(answersGroup!!.checkedRadioButtonId)
        )
        if (selectedButton == quizQuestionList[currentQuestion].correctAnswer) {
            correctAnswers++
        }
        currentQuestion++
    }
}