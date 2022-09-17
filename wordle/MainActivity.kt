package com.example.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import FourLetterWordList
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var count:Int = 0
    var wrd:String = ""
    var answer:String = FourLetterWordList.getRandomFourLetterWord()
    var gameWon = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val answerView = findViewById<TextView>(R.id.answer)
        val button = findViewById<Button>(R.id.btnGuess)
        val restButton = findViewById<Button>(R.id.btnReset)
        restButton.setOnClickListener{
            count = 0
            answerView.visibility = View.INVISIBLE
            answer = FourLetterWordList.getRandomFourLetterWord()
            gameWon = false
            restButton.visibility = View.INVISIBLE
            button.visibility = View.VISIBLE
            startGame(restButton, button, answerView)
        }
        startGame(restButton, button, answerView)
    }

    private fun startGame(reset: Button, button: Button, answerView: TextView){
        val guess1 = findViewById<TextView>(R.id.guess1)
        val res1 = findViewById<TextView>(R.id.result1)
        val guess2 = findViewById<TextView>(R.id.guess2)
        val res2 = findViewById<TextView>(R.id.result2)
        val guess3 = findViewById<TextView>(R.id.guess3)
        val res3 = findViewById<TextView>(R.id.result3)

        val EditText = findViewById<EditText>(R.id.etGuess)

        button.setOnClickListener {
            if(count == 3){
                reset.visibility = View.VISIBLE
                button.visibility = View.INVISIBLE
                answerView.text = answer
                answerView.visibility = View.VISIBLE
                guess1.text = ""
                res1.text = ""
                guess2.text = ""
                res2.text = ""
                guess3.text = ""
                res3.text = ""
                Toast.makeText(it.context, "You've run out of tries", Toast.LENGTH_SHORT).show()
            }else{
                if (EditText.text.toString() != ""){
                    var temp = guess1
                    var tempRes = res1
                    when(count) {
                        1 -> {
                            temp = guess2
                            tempRes = res2
                        }
                        2 -> {
                            temp = guess3
                            tempRes = res3
                        }
                    }

                    wrd = EditText.text.toString().uppercase()
                    temp.text = wrd

                    val gg = checkGuess(wrd, answer)
                    tempRes.text = gg

                    count += 1

                }
            }

        }
    }

    private fun checkGuess(guess: String, wordToGuess:String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
}