package com.example.osexamen

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding


class Spisok : AppCompatActivity() {
    val ArrayInfo = ArrayInfo(1)
    private var DarkMode: Boolean = false
    private var APP_PREFERENCES_DARKMODE = "DarkMode"
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_spisok)
        SwitchToDarkMode()
        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout)

        var CountID = 0
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        DarkMode = prefs.getBoolean(APP_PREFERENCES_DARKMODE, false)
        while (CountID <= ArrayInfo.maxId)
        {
            val b = Button(applicationContext)
            b.text = ArrayInfo.GetQuestion(CountID)//quest[CountID]
            b.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            b.id = CountID + 1
            b.setPadding(5)
            b.text
            if(!DarkMode){
                b.setBackgroundResource(R.drawable.shadow)
                b.setTextColor(resources.getColor(R.color.cardview_dark_background))
            }
            else{
                b.setBackgroundResource(R.drawable.shadowdark)
                b.setTextColor(resources.getColor(R.color.DarkText))
            }
            b.setOnClickListener()
            {
                b.alpha = 0.7f
                SeeQuest(b.id)
            }
//            b?.setOnTouchListener(object : View.OnTouchListener {
//                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//                    if (event!!.getAction()  == MotionEvent.ACTION_DOWN)
//                        b.alpha=0.7f
//                    else if (event!!.getAction()  == MotionEvent.ACTION_UP)
//                        b.alpha=1f
//                    return false
//                }
//            })
            linearLayout.addView(b)
            CountID++
        }

        val button0 = findViewById<Button>(R.id.button0)
        button0?.setOnTouchListener { v, event ->
            if (event!!.action == MotionEvent.ACTION_DOWN)
                button0.alpha = 0.7f
            else if (event.action == MotionEvent.ACTION_UP)
                button0.alpha = 1f
            false
        }
        button0?.setOnClickListener { ExitToMenu() }
    }

    @Suppress("DEPRECATION")
    private fun SwitchToDarkMode() {
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        val back = findViewById<LinearLayout>(R.id.back)
        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout1)
        val maintext = findViewById<TextView>(R.id.label)
        DarkMode = prefs.getBoolean(APP_PREFERENCES_DARKMODE, false)
        if (!DarkMode)
        {
            back.setBackgroundColor(resources.getColor(R.color.white))
            linearLayout.setBackgroundResource(R.drawable.shadow)
            maintext.setTextColor(resources.getColor(R.color.cardview_dark_background))
        }
        else
        {
            back.setBackgroundColor(resources.getColor(R.color.DarkModeBack))
            linearLayout.setBackgroundResource(R.drawable.shadowdark)
            maintext.setTextColor(resources.getColor(R.color.DarkText))
        }
    }

    fun SeeQuest(a: Int) {
        val showQueste = Intent(this, ShowQuest::class.java)
        showQueste.putExtra("quest", ArrayInfo.GetQuestion(a-1))
        showQueste.putExtra("answer", ArrayInfo.GetAnswer(a-1))
        startActivity(showQueste)
    }

    fun ExitToMenu() {
        this.finish()
    }
}
