package my.app.examus

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class Test : AppCompatActivity() {
    private var count = 0
    private var questInt = arrayOfNulls<Int>(10)
    private var doubleclick = false
    private val ArrayInfo = ArrayInfo(1)
    private lateinit var button: Button
    private lateinit var label1: TextView
    private lateinit var textBox1: TextView
    private lateinit var textBox2: TextView
    private lateinit var photobox: ImageView
    private var DarkMode: Boolean = false
    private var ShowNum: Boolean = false
    private var APP_PREFERENCES_DARKMODE = "DarkMode"

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_test)
        SwitchToDarkMode()
        ShowNum()
        button = findViewById(R.id.button)
        label1 = findViewById(R.id.label1)
        textBox1 = findViewById(R.id.textBox1)
        textBox2 = findViewById(R.id.textBox2)
        photobox = findViewById(R.id.imageView)

        button.setOnTouchListener { v, event ->
            if (event!!.action == MotionEvent.ACTION_DOWN)
                button.alpha = 0.7f
            else if (event.action == MotionEvent.ACTION_UP)
                button.alpha = 1f
            false
        }

        button.setOnClickListener()
        {
            ButtonClick()
        }
    }

    @Suppress("DEPRECATION")
    private fun SwitchToDarkMode() {
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        val back = findViewById<LinearLayout>(R.id.back)
        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout)
        val maintext = findViewById<TextView>(R.id.label1)
        DarkMode = prefs.getBoolean(APP_PREFERENCES_DARKMODE, false)
        if (!DarkMode) {
            back.setBackgroundColor(resources.getColor(R.color.white))
            linearLayout.setBackgroundResource(R.drawable.shadow)
            maintext.setTextColor(resources.getColor(R.color.cardview_dark_background))
        } else {
            back.setBackgroundColor(resources.getColor(R.color.DarkModeBack))
            linearLayout.setBackgroundResource(R.drawable.shadowdark)
            maintext.setTextColor(resources.getColor(R.color.DarkText))
        }
    }

    @SuppressLint("SetTextI18n")
    fun FirstClick() {
        photobox.setImageDrawable(null)
        button.text = "Answer"
        var temp = (0..ArrayInfo.maxId).random()
        while (temp in questInt)
            temp = (0..ArrayInfo.maxId).random()
        if (!ShowNum)
            label1.text = "Вопрос " + (count + 1)
        else
            label1.text = "Вопрос " + (count + 1) + " (№" + (temp + 1).toString() + ")"
        questInt[count] = temp
        textBox2.text = ""
        textBox1.text = ArrayInfo.GetQuestion(temp)//quest[temp]
        doubleclick = true
    }

    @SuppressLint("SetTextI18n")
    fun SecondClick() {
        when {
            questInt[count] == 11 -> {
                photobox.setImageResource(R.drawable.p11)
            }
            questInt[count] == 15 -> {
                photobox.setImageResource(R.drawable.p15)
            }
            questInt[count] == 29 -> {
                photobox.setImageResource(R.drawable.p29)
            }
        }
        button.text = "Question"
        if (!ShowNum)
            textBox2.text = "Вопрос " + ArrayInfo.GetQuestion(questInt[count]!!)
        else
            textBox2.text =
                "Вопрос (№" + (questInt[count]!! + 1).toString() + "):\n" + ArrayInfo.GetQuestion(
                    questInt[count]!!
                )

        //textBox2.text = "Вопрос: " + ArrayInfo.GetQuestion(questInt[count]!!)//quest[questInt[count]!!]
        if (!ShowNum)
            textBox1.text =
                "\n\nОтвет: " + ArrayInfo.GetAnswer(questInt[count]!!)//answer[questInt[count]!!]
        else
            textBox1.text =
                "\n\nОтвет: \n" + ArrayInfo.GetAnswer(questInt[count]!!)//answer[questInt[count]!!]
        count++
        doubleclick = false
        if (count == 10) {
            button.text = "Exit"
        }
    }

    fun ShowNum() {
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        APP_PREFERENCES_DARKMODE = "ShowNum"
        ShowNum = prefs.getBoolean(APP_PREFERENCES_DARKMODE, false)
        APP_PREFERENCES_DARKMODE = "DarkMode"
    }

    fun ButtonClick() {
        if (count == 10) {
            count = 0
            this.finish()
        } else {
            if (doubleclick)
                SecondClick()
            else
                FirstClick()
        }
    }
}