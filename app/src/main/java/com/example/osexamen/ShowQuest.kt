package com.example.osexamen

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


class ShowQuest : AppCompatActivity() {
    private var DarkMode: Boolean = false
    private var APP_PREFERENCES_DARKMODE = "DarkMode"
    @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_show_quest)
        val textBox1 = findViewById<TextView>(R.id.textBox1)
        val textBox2 = findViewById<TextView>(R.id.textBox2)
        SwitchToDarkMode()
        val arguments = intent.extras
        val quest = arguments!!["quest"].toString()
        val answer = arguments["answer"].toString()
        textBox2.text = "Вопрос: $quest"
        textBox1.text = "\n\nОтвет: $answer"
        val button0 = findViewById<Button>(R.id.button)
        button0.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (event!!.action == MotionEvent.ACTION_DOWN)
                    button0.alpha = 0.7f
                else if (event.action == MotionEvent.ACTION_UP)
                    button0.alpha = 1f
                return false
            }
        })
        button0?.setOnClickListener {
            ExitToMenu()
        }
    }

    private fun SwitchToDarkMode() {
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        val back = findViewById<LinearLayout>(R.id.back)
        DarkMode = prefs.getBoolean(APP_PREFERENCES_DARKMODE, false)
        if (!DarkMode) {
            back.setBackgroundColor(resources.getColor(R.color.white))
        } else {
            back.setBackgroundColor(resources.getColor(R.color.DarkModeBack))
        }
    }

    fun ExitToMenu() {
        this.finish()
    }
}