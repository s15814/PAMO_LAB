package com.s15814.healthApp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;


import java.util.ArrayList;
import java.util.List;

public class Quiz extends Fragment {
    private List<QuizQuestion> quizQuestionList = new ArrayList<>();
    private TextView questionText;
    private RadioButton firstAnswer, secondAnswer;
    private RadioGroup answersGroup;
    private Button nextButton;
    private int selectedButton;

    private int currentQuestion;
    private int correctAnswers;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.quiz, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        quizQuestionList.add(new QuizQuestion("Should you wash your hand often to prevent coronavirus?", "Yes", "No", 0));
        quizQuestionList.add(new QuizQuestion("Should you self-isolate to stay safe?", "Yes", "No", 0));
        quizQuestionList.add(new QuizQuestion("Where did coronavirus originate from?", "China", "Poland", 0));

        questionText = (TextView) view.findViewById(R.id.questionText);
        answersGroup = (RadioGroup) view.findViewById(R.id.answerRadioGroup);
        firstAnswer = (RadioButton) view.findViewById(R.id.firstAnswerButton);
        secondAnswer = (RadioButton) view.findViewById(R.id.secondAnswerButton);
        nextButton = (Button) view.findViewById(R.id.quizNextQuestionButton);

        answersGroup.clearCheck();
        nextQuestion();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestion < quizQuestionList.size()) {
                    checkAnswer();
                    nextQuestion();
                } else {
                    String quizOverText = "Quiz over.";
                    questionText.setText(quizOverText);
                }
            }
        });

        view.findViewById(R.id.quizBackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(Quiz.this)
                        .navigate(R.id.action_quiz_to_mainMenu);
            }
        });
    }

    private void nextQuestion() {
        answersGroup.clearCheck();
        if (currentQuestion < quizQuestionList.size()) {
            questionText.setText(quizQuestionList.get(currentQuestion).getQuestion());
            firstAnswer.setText(quizQuestionList.get(currentQuestion).getAnswer1());
            secondAnswer.setText(quizQuestionList.get(currentQuestion).getAnswer2());
        } else {
            String resultText = "Quiz finished, you scored " + correctAnswers + "/3 points.";
            questionText.setText(resultText);
        }
        answersGroup.clearCheck();
    }

    private void checkAnswer() {
        selectedButton = this.answersGroup.indexOfChild(
                getView().findViewById(this.answersGroup.getCheckedRadioButtonId())
        );
        if (selectedButton == quizQuestionList.get(currentQuestion).getCorrectAnswer()) {
            correctAnswers++;
        }
        currentQuestion++;
    }
}