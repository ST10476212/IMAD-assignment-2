package vcmsa.ci.imadassignment2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ScoreScreen : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score_screen)

        val score = intent.getIntExtra("score", 0)
        val wrongList = intent.getStringArrayListExtra("wrongList")

        val scoreText: TextView = findViewById(R.id.ScoreText)
        val wrongText: TextView = findViewById(R.id.WrongText)
        val restartButton: Button = findViewById(R.id.RestartButton)

        scoreText.text = "Your Score: $score / 4"

        if (wrongList.isNullOrEmpty()) {
            wrongText.text = "YOU GOT ALL THE QUESTIONS RIGHT!!"
        } else {
            val wrongFormatted = wrongList.joinToString("\n- ", prefix = "Questions you got wrong:\n- ")
            wrongText.text = wrongFormatted

            restartButton.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                // Clear the back stack so user cant press  "Back" to return to results
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        }
    }
}