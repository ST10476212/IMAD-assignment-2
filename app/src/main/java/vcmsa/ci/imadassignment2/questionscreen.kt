package vcmsa.ci.imadassignment2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class questionscreen : AppCompatActivity() {

    private val questions = arrayOf(
        "Nelson Mandela was the first president after apartheid.",
        "The apartheid regime in South Africa was established in the 19th century.",
        "The San people are the indigenous people of South Africa.",
        "The Boer Wars were fought between the British and the Boer Republics."
    )

    private val answers = booleanArrayOf(
        true,
        false,
        true,
        true // Corrected answer for the Boer Wars question
    )

    private var currentIndex = 0
    private var score = 0
    private val wrongQuestion = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_questionscreen)



            val trueButton: Button = findViewById(R.id.trueButton)
            val falseButton: Button = findViewById(R.id.falseButton)

            trueButton.setOnClickListener {
                checkAnswer(true)
            }

            falseButton.setOnClickListener {
                checkAnswer(false)
            }

            updateQuestion()
        }

        private fun updateQuestion() {
            if (currentIndex < questions.size) {
                val questionText: TextView = findViewById(R.id.displayQuestion)
                questionText.text = questions[currentIndex]
            }
        }

        private fun checkAnswer(userAnswer: Boolean) {
            val correctAnswer = answers[currentIndex]
            val currentQuestion = questions[currentIndex]

            if (userAnswer == correctAnswer) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
                score++
            } else {
                Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show()
                wrongQuestion.add(currentQuestion)
            }

            currentIndex++
            if (currentIndex < questions.size) {
                updateQuestion()
            } else {
                val intent = Intent(this, ScoreScreen::class.java)
                intent.putExtra("score", score)
                intent.putStringArrayListExtra("wrongList", wrongQuestion)
                startActivity(intent)
                finish()
            }
        }
    }
