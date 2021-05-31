package com.example.osexamen

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


class MainActivity : AppCompatActivity() {
    private var DarkMode: Boolean = false
    private var APP_PREFERENCES_DARKMODE = "DarkMode"

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        val button1 = findViewById<Button>(R.id.button1)
        val settings = findViewById<ImageView>(R.id.settings)
        SwitchToDarkMode()
        button1?.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (event!!.action == MotionEvent.ACTION_DOWN)
                    button1.alpha = 0.7f
                else if (event.action == MotionEvent.ACTION_UP)
                    button1.alpha = 1f
                return false
            }
        })
        button1?.setOnClickListener()
        { TestOS() }
        settings?.setOnClickListener()
        { Settings() }
        settings?.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (event!!.action == MotionEvent.ACTION_DOWN)
                    settings.alpha = 0.7f
                else if (event.action == MotionEvent.ACTION_UP)
                    settings.alpha = 1f
                return false
            }
        })
        val button2 = findViewById<Button>(R.id.button2)
        button2?.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (event!!.action == MotionEvent.ACTION_DOWN)
                    button2.alpha = 0.7f
                else if (event.action == MotionEvent.ACTION_UP)
                    button2.alpha = 1f
                return false
            }
        })
        button2?.setOnClickListener { SpisokOS() }
        val button4 = findViewById<Button>(R.id.button4)
        button4?.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (event!!.action == MotionEvent.ACTION_DOWN)
                    button4.alpha = 0.7f
                else if (event.action == MotionEvent.ACTION_UP)
                    button4.alpha = 1f
                return false
            }
        })
        button4?.setOnClickListener { message() }
        val button5 = findViewById<Button>(R.id.button5)
        button5?.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (event!!.action == MotionEvent.ACTION_DOWN)
                    button5.alpha = 0.7f
                else if (event.action == MotionEvent.ACTION_UP)
                    button5.alpha = 1f
                return false
            }
        })
        button5?.setOnClickListener { message() }
        val button6 = findViewById<Button>(R.id.button6)
        button6?.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (event!!.action == MotionEvent.ACTION_DOWN)
                    button6.alpha = 0.7f
                else if (event.action == MotionEvent.ACTION_UP)
                    button6.alpha = 1f
                return false
            }
        })
        button6?.setOnClickListener {
            message()
        }
        val button7 = findViewById<Button>(R.id.button7)
        button7?.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (event!!.action == MotionEvent.ACTION_DOWN)
                    button7.alpha = 0.7f
                else if (event.action == MotionEvent.ACTION_UP)
                    button7.alpha = 1f
                return false
            }
        })
        button7?.setOnClickListener {
            message()
        }
    }

    private fun SwitchToDarkMode() {
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        val textMenu = findViewById<TextView>(R.id.textMenu)
        val textView2 = findViewById<TextView>(R.id.textView2)
        val textView3 = findViewById<TextView>(R.id.textView3)
        val textView4 = findViewById<TextView>(R.id.textView4)
        val linearLayout1 = findViewById<LinearLayout>(R.id.linearLayout1)
        val linearLayout2 = findViewById<LinearLayout>(R.id.linearLayout2)
        val linearLayout3 = findViewById<LinearLayout>(R.id.linearLayout3)
        val linearLayout4 = findViewById<LinearLayout>(R.id.linearLayout4)
        val back = findViewById<LinearLayout>(R.id.back)
        DarkMode = prefs.getBoolean(APP_PREFERENCES_DARKMODE, false)
        if (!DarkMode)
        {
            linearLayout1.setBackgroundResource(R.drawable.shadow)
            linearLayout2.setBackgroundResource(R.drawable.shadow)
            linearLayout3.setBackgroundResource(R.drawable.shadow)
            linearLayout4.setBackgroundResource(R.drawable.shadow)
            textMenu.setTextColor(resources.getColor(R.color.cardview_dark_background))
            textView2.setTextColor(resources.getColor(R.color.cardview_dark_background))
            textView3.setTextColor(resources.getColor(R.color.cardview_dark_background))
            textView4.setTextColor(resources.getColor(R.color.cardview_dark_background))
            back.setBackgroundColor(resources.getColor(R.color.white))
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        else
        {
            linearLayout1.setBackgroundResource(R.drawable.shadowdark)
            linearLayout2.setBackgroundResource(R.drawable.shadowdark)
            linearLayout3.setBackgroundResource(R.drawable.shadowdark)
            linearLayout4.setBackgroundResource(R.drawable.shadowdark)
            textMenu.setTextColor(Color.parseColor("#EEE8D5"))
            textView2.setTextColor(resources.getColor(R.color.DDarkText))
            textView3.setTextColor(resources.getColor(R.color.DDarkText))
            textView4.setTextColor(resources.getColor(R.color.DDarkText))
            back.setBackgroundColor(resources.getColor(R.color.DarkModeBack))
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    private fun TestOS() {
        val testOS = Intent(this, Test::class.java)
        startActivity(testOS)
    }

    private fun Settings() {
        val set = Intent(this, settings::class.java)
        startActivity(set)
    }

    private fun SpisokOS() {
        val spisOS = Intent(this, Spisok::class.java)
        startActivity(spisOS)
    }

    private fun message() {
        val text = "Пока не завезли :c"
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.setGravity(Gravity.BOTTOM, 0, 0)
        toast.show()
    }
}