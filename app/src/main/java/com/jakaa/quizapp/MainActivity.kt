package com.jakaa.quizapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jakaa.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Step 1: setup Binding
    private lateinit var binding: ActivityMainBinding

    //Step 2: Create Question options and answer array
    // Question array
    private val questions =  arrayOf(
        "What is the built-in database in Android Studio?",
        "What is the full form of APK in Android Development?",
        "In which year, first android was release by Google?"
    )

    // Option array
    private val option = arrayOf(
        arrayOf("MySQL", "SQLite", "Firebase"),
        arrayOf("Application Programming Interface", "Android Programming Interface", "Android Package Information"),
        arrayOf("2010", "2006", "2008")
    )

    // Answer array
    private val correctAnswer = arrayOf(
        1, 0, 2
    )

    // Step 3: Create Question Index and score Value
    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Step 11: Call displayQuestion method
        displayQuestion()

        //Step 12: Call method inside click Listeners
        binding.option1Button.setOnClickListener {
            checkAnswer(0)
        }
        binding.option2Button.setOnClickListener {
            checkAnswer(1)
        }
        binding.option3Button.setOnClickListener {
            checkAnswer(2)
        }
        binding.restartButton.setOnClickListener {
            restartQuiz()
        }


    }
    //Step 4: CorrectButton color() Method.
    private fun correctButtonColors(buttonIndex: Int){
        when(buttonIndex){
            0 -> binding.option1Button.setBackgroundColor(Color.GREEN)
            1 -> binding.option2Button.setBackgroundColor(Color.GREEN)
            2 -> binding.option3Button.setBackgroundColor(Color.GREEN)
        }
    }

    //Step 5: Create wrong Button color Method
    private fun wrongButtonColors(buttonIndex: Int){
        when(buttonIndex){
            0 -> binding.option1Button.setBackgroundColor(Color.RED)
            1 -> binding.option2Button.setBackgroundColor(Color.RED)
            2 -> binding.option3Button.setBackgroundColor(Color.RED)
        }
    }

    //Step 6: Reset Button Color Method
    private fun resetButtonColors(){
        binding.option1Button.setBackgroundColor(Color.rgb(50, 59, 96))
        binding.option2Button.setBackgroundColor(Color.rgb(50, 59, 96))
        binding.option3Button.setBackgroundColor(Color.rgb(50, 59, 96))
    }

    //Step 7: Show result method.
    private fun showResult(){
        Toast.makeText(this, "Your score: $score out of ${questions.size}", Toast.LENGTH_LONG).show()
        binding.restartButton.isEnabled = true
    }

    //Step 8: Display Question
    private fun displayQuestion(){
        binding.QuestionText.text = questions[currentQuestionIndex]
        binding.option1Button.text = option[currentQuestionIndex][0]
        binding.option2Button.text = option[currentQuestionIndex][1]
        binding.option3Button.text = option[currentQuestionIndex][2]
        resetButtonColors()
    }
    //Step 9: CheckAnswers method
    private fun checkAnswer(selectedAnswerIndex: Int){
        val correctAnswerIndex = correctAnswer[currentQuestionIndex]

        if (correctAnswerIndex == selectedAnswerIndex){
            score++
            correctButtonColors(selectedAnswerIndex)
        }else{
            wrongButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }

        if (currentQuestionIndex < questions.size){
            currentQuestionIndex++
            binding.QuestionText.postDelayed({displayQuestion()}, 1000)
        }else{
            showResult()
        }
    }
    //Step 10: restartQuiz Method

    private fun restartQuiz(){
        currentQuestionIndex = 0
        score = 0
        displayQuestion()
        binding.restartButton.isEnabled = false
    }

}




























































































