package com.example.osexamen

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import android.widget.Switch
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

        DarkMode = prefs.getBoolean(APP_PREFERENCES_DARKMODE, false)
        switch.isChecked = DarkMode
        button1?.setOnClickListener()
        {
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
            val editor = prefs.edit()
            if (isChecked) {
                DarkMode = true
                editor.putBoolean(APP_PREFERENCES_DARKMODE, DarkMode).apply()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                DarkMode = false
                editor.putBoolean(APP_PREFERENCES_DARKMODE, DarkMode).apply()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}