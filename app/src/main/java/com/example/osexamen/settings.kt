package com.example.osexamen

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


class settings : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences
    private var DarkMode: Boolean = false
    private var APP_PREFERENCES_DARKMODE = "DarkMode"
    private var ShowNum = false
    private var countTap = 0

    @SuppressLint("ClickableViewAccessibility", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_settings)
        prefs = getSharedPreferences("settings", Context.MODE_PRIVATE)
        val button1 = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.donat)
        val button3 = findViewById<Button>(R.id.connection)
        val button4 = findViewById<Button>(R.id.bugreport)
        val button5 = findViewById<Button>(R.id.youtube)
        val button6 = findViewById<Button>(R.id.alinka)
        val switch = findViewById<Switch>(R.id.switch1)
        val switch2 = findViewById<Switch>(R.id.switch2)
        val amogus = findViewById<ImageView>(R.id.amogus)
        SwitchTheme()
        DarkMode = prefs.getBoolean(APP_PREFERENCES_DARKMODE, false)
        APP_PREFERENCES_DARKMODE = "ShowNum"
        ShowNum = prefs.getBoolean(APP_PREFERENCES_DARKMODE, false)
        APP_PREFERENCES_DARKMODE = "DarkMode"
        switch2.isChecked = ShowNum
        switch.isChecked = DarkMode
        button1?.setOnClickListener()
        {
            val editor = prefs.edit()
            if (DarkMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            editor.putBoolean(APP_PREFERENCES_DARKMODE, DarkMode).apply()
            APP_PREFERENCES_DARKMODE = "ShowNum"
            editor.putBoolean(APP_PREFERENCES_DARKMODE, ShowNum).apply()
            APP_PREFERENCES_DARKMODE = "DarkMode"
            SwitchTheme()
            this.finish()
        }
        button2?.setOnClickListener() {
            intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.donationalerts.com/r/alon_prog"));
            startActivity(intent);
        }

        button3?.setOnClickListener() {
            val mailto = "mailto:valerij.shishov@gmail.com" +
                    "?cc=" +
                    "&subject=" + Uri.encode("Examus + Helpanus") +
                    "&body=" + Uri.encode("Привет, хочу поучаствовать в проекте Examus! Могу предоставить вопросы с ответами или что-нибудь другое!")
            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse(mailto)
            try {
                startActivity(emailIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(applicationContext, "Error to open email app", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        button4?.setOnClickListener() {
            val mailto = "mailto:valerij.shishov@gmail.com" +
                    "?cc=" +
                    "&subject=" + Uri.encode("Examus + Bug") +
                    "&body=" + Uri.encode("Привет, я нашёл проблему в приложении Examus!")
            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse(mailto)
            try {
                startActivity(emailIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(applicationContext, "Error to open email app", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        button5?.setOnClickListener() {
            intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UC3GTlMdQ4d77rETLkMUKwIA"));
            startActivity(intent);
        }

        button6?.setOnClickListener() {
            intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/molereyka"));
            startActivity(intent);
        }

        button1?.setOnTouchListener { v, event ->
            if (event!!.action == MotionEvent.ACTION_DOWN)
                button1.alpha = 0.7f
            else if (event.action == MotionEvent.ACTION_UP)
                button1.alpha = 1f
            false
        }

        amogus?.setOnClickListener() {
            countTap++
            if(countTap==5){
                countTap=0
                Amogus()
            }
        }

        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            DarkMode = isChecked
        }
        switch2.setOnCheckedChangeListener { buttonView, isChecked ->
            ShowNum = isChecked
        }
    }

    private fun Amogus(){
        var mediaPlayer: MediaPlayer = MediaPlayer.create(this,R.raw.amogus)
        mediaPlayer.start()
        val toast = Toast.makeText(applicationContext, "AMOGUS!", Toast.LENGTH_SHORT)
        toast.show()
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