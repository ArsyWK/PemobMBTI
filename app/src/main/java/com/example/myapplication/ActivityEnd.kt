package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ActivityEnd : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entp)

        val resultText = findViewById<TextView>(R.id.textDescription)
        resultText.text = QuestionnaireActivity.ResultPersonality
    }
}
