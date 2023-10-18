package com.example.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

private const val TAG = "MainActivity"

data class Question(val text: String, val answer: Boolean)

class MainActivity : AppCompatActivity() {

    private val questions = listOf(
        Question("Sunce je zvijezda.", true),
        Question("Mjesec je zvijezda.", false),
        Question("Zemlja je ravna ploča.", false),
        Question("Postoji osam planeta u našem solarnom sustavu.", true),
        Question("Android je programski jezik.", false)
    )

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)
        nextButton = findViewById(R.id.nextButton)
        questionTextView = findViewById(R.id.questionTextView)

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questions.size
            updateQuestion()
        }

        updateQuestion()
    }

    private fun updateQuestion() {
        val questionText = questions[currentIndex].text
        questionTextView.text = questionText
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questions[currentIndex].answer

        val toastMessage = if (userAnswer == correctAnswer) {
            "Točan"
        } else {
            "Netočan"
        }

        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
    }
}
