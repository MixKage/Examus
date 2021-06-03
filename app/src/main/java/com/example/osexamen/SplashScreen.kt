package com.example.osexamen

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate

class SplashScreen : AppCompatActivity() {
    private var DarkMode: Boolean = false
    private var APP_PREFERENCES_DARKMODE = "DarkMode"
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_OSexamen)
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        SwitchToDarkModeApp()
        setContentView(R.layout.activity_splash_screen)
        SwitchToDarkMode()
        startActivity(Intent(this,MainActivity::class.java))
        finish()
//        val SPLASH_TIME_OUT:Long = 1000 // 1 sec
//        Handler().postDelayed({
//            // This method will be executed once the timer is over
//            // Start your app main activity
//
//
//            // close this activity
//
//        }, SPLASH_TIME_OUT)
    }
    private fun SwitchToDarkModeApp()
    {
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        DarkMode = prefs.getBoolean(APP_PREFERENCES_DARKMODE, false)
        if (!DarkMode)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
    private fun SwitchToDarkMode() {
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        val textExamus = findViewById<TextView>(R.id.Examus)
        val back = findViewById<LinearLayout>(R.id.back)
        DarkMode = prefs.getBoolean(APP_PREFERENCES_DARKMODE, false)
        if (!DarkMode)
        {
            textExamus.setTextColor(resources.getColor(R.color.cardview_dark_background))
            back.setBackgroundColor(resources.getColor(R.color.white))
        }
        else
        {
            textExamus.setTextColor(resources.getColor(R.color.DDarkText))
            back.setBackgroundColor(resources.getColor(R.color.DarkModeBack))
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}