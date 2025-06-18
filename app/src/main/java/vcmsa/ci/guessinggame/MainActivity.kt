package vcmsa.ci.guessinggame

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private var randomNumber: Int = 0
    private var etGuessInput: EditText? = null
    private var tvOutput: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        generateRandomNumber()

        etGuessInput = findViewById(R.id.etGuessInput)
        tvOutput = findViewById(R.id.tvOutput)

        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val btnClear = findViewById<Button>(R.id.btnClear)
        val btnExit = findViewById<Button>(R.id.btnExit)

        btnSubmit.setOnClickListener {
            compare()
        }

        btnClear.setOnClickListener {
            etGuessInput?.text?.clear()
            generateRandomNumber()
            tvOutput?.text = ""
        }
        btnExit.setOnClickListener {
            moveTaskToBack(true)
            android.os.Process.killProcess(android.os.Process.myPid())
            exitProcess(1)
            }
        }
        private fun generateRandomNumber(){
            randomNumber = Random.nextInt(1,100)
        }
    private fun isNotEmpty() : Boolean {
        var b = true
        if (etGuessInput?.text.toString().trim().isEmpty()){
            etGuessInput?.error = "Input Required"
            b = false
        }
        return b
    }
        private fun compare(){
            if (isNotEmpty()) {
                val etGuessInput = etGuessInput?.text.toString().trim().toInt()

                if (etGuessInput == randomNumber) {
                    tvOutput?.text = "Your guess was correct"
                } else if (etGuessInput > randomNumber) {
                    tvOutput?.text = "Your guess was too high"
                } else {
                    tvOutput?.text = "Your Guess is too low"
                }
            }
    }
}
