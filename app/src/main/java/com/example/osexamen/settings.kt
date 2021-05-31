package com.example.osexamen

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class settings : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences
    private var DarkMode: Boolean = false
    private var APP_PREFERENCES_DARKMODE = "DarkMode"

    @SuppressLint("ClickableViewAccessibility", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_settings)
        prefs = getSharedPreferences("settings", Context.MODE_PRIVATE)
        val button1 = findViewById<Button>(R.id.button)
        val switch = findViewById<Switch>(R.id.switch1)
        SwitchTheme()
        DarkMode = prefs.getBoolean(APP_PREFERENCES_DARKMODE, false)
        switch.isChecked = DarkMode
        button1?.setOnClickListener()
        {
            val editor = prefs.edit()
            if (DarkMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor.putBoolean(APP_PREFERENCES_DARKMODE, DarkMode).apply()
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor.putBoolean(APP_PREFERENCES_DARKMODE, DarkMode).apply()
            }
            SwitchTheme()
            this.finish()
        }

        button1?.setOnTouchListener { v, event ->
            if (event!!.action == MotionEvent.ACTION_DOWN)
                button1.alpha = 0.7f
            else if (event.action == MotionEvent.ACTION_UP)
                button1.alpha = 1f
            false
        }

        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            DarkMode = isChecked
        }
    }

    private fun SwitchTheme() {
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        val textMenu = findViewById<TextView>(R.id.label1)
        val linearLayout1 = findViewById<LinearLayout>(R.id.linearLayout)
        val back = findViewById<LinearLayout>(R.id.back)
        DarkMode = prefs.getBoolean(APP_PREFERENCES_DARKMODE, false)
        if (!DarkMode) {
            linearLayout1.setBackgroundResource(R.drawable.shadow)
            textMenu.setTextColor(resources.getColor(R.color.cardview_dark_background))
            back.setBackgroundColor(resources.getColor(R.color.white))
        } else {
            linearLayout1.setBackgroundResource(R.drawable.shadowdark)
            textMenu.setTextColor(resources.getColor(R.color.DarkText))
            back.setBackgroundColor(resources.getColor(R.color.DarkModeBack))
        }
    }
}