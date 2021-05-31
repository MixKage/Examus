package com.example.osexamen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


class MainActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        val button1 = findViewById<Button>(R.id.button1)
        val settings = findViewById<ImageView>(R.id.settings)
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
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }//DEL
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
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }//DEL
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