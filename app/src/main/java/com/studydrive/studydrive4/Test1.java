package com.studydrive.studydrive4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Test1 extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsTextView;
    ImageView imageView;
    TextView questiomTextView;
    Button ansA, ansB, ansC;
    Button submitBtn;

    int score = 0;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";
    public int currentTest = MyAdapter.currentTest;
    int totalQuestion = QuestionAnswer1.question[currentTest].length;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);

        totalQuestionsTextView = findViewById(R.id.total_question1);
        imageView = findViewById(R.id.image1);
        questiomTextView = findViewById(R.id.question1);
        ansA = findViewById(R.id.Ans_A1);
        ansB = findViewById(R.id.Ans_B1);
        ansC = findViewById(R.id.Ans_C1);
        submitBtn = findViewById(R.id.submit_btn1);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        submitBtn.setOnClickListener(this);


        totalQuestionsTextView.setText("Осталось вопросов: " + (totalQuestion - currentQuestionIndex));
        loadNewQuestion();


    }

    @Override
    public void onClick(View view) {

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.submit_btn1){

            if (selectedAnswer.equals(QuestionAnswer1.correctAnswers[currentTest][currentQuestionIndex])) {
                score++;
            }
            currentQuestionIndex++;
            totalQuestionsTextView.setText("Осталось вопросов: " + (totalQuestion - currentQuestionIndex));
            loadNewQuestion();

        } else {

            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.BLUE);

        }

    }


    void loadNewQuestion() {

        if (currentQuestionIndex == totalQuestion) {
            finishQuiz();
            return;
        }

        questiomTextView.setText(QuestionAnswer1.question[currentTest][currentQuestionIndex]);
        ansA.setText(QuestionAnswer1.choices[currentTest][currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer1.choices[currentTest][currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer1.choices[currentTest][currentQuestionIndex][2]);
        imageView.setImageResource(QuestionAnswer1.imageList[currentTest][currentQuestionIndex]);

    }


    void finishQuiz() {
        String passStatus = "";
        if (score > totalQuestion*0.90) {
            passStatus = "Тест пройден";
        } else {
            passStatus = "Тест провален";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Вы набрали "+score +" из "+totalQuestion+" баллов")
                .setPositiveButton("Вернуться в меню", (dialogInterface, i) -> goBackHome())
                .setCancelable(false)
                .show();

    }


    void goBackHome() {
        score = 0;
        currentQuestionIndex = 0;
        Intent intent = new Intent(Test1.this, MainActivity.class);
        startActivity(intent);
    }


}