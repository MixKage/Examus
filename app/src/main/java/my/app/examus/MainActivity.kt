package my.app.examus

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.MotionEvent
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private var DarkMode: Boolean = false
    private var APP_PREFERENCES_DARKMODE = "DarkMode"
    private var mode = 1

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        window.navigationBarColor = resources.getColor(R.color.black)
        setContentView(R.layout.activity_main)
        SwitchToDarkMode()
        val button1 = findViewById<Button>(R.id.button1)
        val settings = findViewById<ImageView>(R.id.settings)
        val alpha = settings.alpha
        button1?.setOnClickListener()
        {
            mode = 1
            testActivity()
        }
        settings?.setOnClickListener()
        { settings() }
        settings?.setOnTouchListener { _, event ->
            if (event!!.action == MotionEvent.ACTION_DOWN)
                settings.alpha = alpha - 0.3f
            else if (event.action == MotionEvent.ACTION_UP)
                settings.alpha = alpha
            false
        }
        val button2 = findViewById<Button>(R.id.button2)
        button2?.setOnClickListener()
        {
            mode = 1
            listQuestion()
        }
        val button4 = findViewById<Button>(R.id.button4)
        button4?.setOnClickListener()
        {
            val NewMainActivity = Intent(this, NewMain::class.java)
            startActivity(NewMainActivity)//ВЫЛЕТАЕТ
//            mode = 2
//            testActivity()
        }
        val button5 = findViewById<Button>(R.id.button5)
        button5?.setOnClickListener()
        {
            message()
//            mode =2
//            listQuestion()
        }
        val button6 = findViewById<Button>(R.id.button6)
        button6?.setOnClickListener {
            message()
        }
        val button7 = findViewById<Button>(R.id.button7)
        button7?.setOnClickListener {
            message()
        }
        val newmenu = findViewById<Button>(R.id.newmenu)
        newmenu?.setOnClickListener {
            val prefs = getSharedPreferences("settings", MODE_PRIVATE)
            DarkMode = prefs.getBoolean(APP_PREFERENCES_DARKMODE, false)
            if (DarkMode){
                val text = "В новом меню тёмная версия не работает и ломает приложение!"
                val duration = Toast.LENGTH_LONG

                val toast = Toast.makeText(applicationContext, text, duration)
                toast.setGravity(Gravity.BOTTOM, 0, 0)
                toast.show()
            }
            val NewMainActivity = Intent(this, NewMain::class.java)
            startActivity(NewMainActivity)
        }
    }

    private fun SwitchToDarkMode() {
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        val textMenu = findViewById<TextView>(R.id.textMenu)
        val textView2 = findViewById<TextView>(R.id.textView2)
        val textView3 = findViewById<TextView>(R.id.textView3)
        val textView4 = findViewById<TextView>(R.id.textView4)
        val textViewNewMenu = findViewById<TextView>(R.id.NewMenuText)
        val linearLayout1 = findViewById<LinearLayout>(R.id.linearLayout1)
        val linearLayout2 = findViewById<LinearLayout>(R.id.linearLayout2)
        val linearLayout3 = findViewById<LinearLayout>(R.id.linearLayout3)
        val linearLayout4 = findViewById<LinearLayout>(R.id.linearLayout4)
        val settings = findViewById<ImageView>(R.id.settings)
        val back = findViewById<LinearLayout>(R.id.back)
        DarkMode = prefs.getBoolean(APP_PREFERENCES_DARKMODE, false)
        if (!DarkMode) {
            linearLayout1.setBackgroundResource(R.drawable.shadow)
            linearLayout2.setBackgroundResource(R.drawable.shadow)
            linearLayout3.setBackgroundResource(R.drawable.shadow)
            linearLayout4.setBackgroundResource(R.drawable.shadow)
            textMenu.setTextColor(resources.getColor(R.color.cardview_dark_background))
            textView2.setTextColor(resources.getColor(R.color.cardview_dark_background))
            textView3.setTextColor(resources.getColor(R.color.cardview_dark_background))
            textView4.setTextColor(resources.getColor(R.color.cardview_dark_background))
            textViewNewMenu.setTextColor(resources.getColor(R.color.cardview_dark_background))
            back.setBackgroundColor(resources.getColor(R.color.white))
            settings.alpha = 1f
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            linearLayout1.setBackgroundResource(R.drawable.shadowdark)
            linearLayout2.setBackgroundResource(R.drawable.shadowdark)
            linearLayout3.setBackgroundResource(R.drawable.shadowdark)
            linearLayout4.setBackgroundResource(R.drawable.shadowdark)
            textMenu.setTextColor(Color.parseColor("#EEE8D5"))
            textView2.setTextColor(resources.getColor(R.color.DDarkText))
            textView3.setTextColor(resources.getColor(R.color.DDarkText))
            textView4.setTextColor(resources.getColor(R.color.DDarkText))
            textViewNewMenu.setTextColor(resources.getColor(R.color.DDarkText))
            back.setBackgroundColor(resources.getColor(R.color.DarkModeBack))
            settings.alpha = 0.8f
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    private fun testActivity() {
        val testActivity = Intent(this, Test::class.java)
        testActivity.putExtra("mode", mode.toString())
        startActivity(testActivity)
    }

    private fun settings() {
        val set = Intent(this, settings::class.java)
        startActivity(set)
    }

    private fun listQuestion() {
        val listQuestion = Intent(this, Spisok::class.java)
        listQuestion.putExtra("mode", mode.toString())
        startActivity(listQuestion)
    }

    private fun message() {
        val text = "Пока не завезли :c"
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.setGravity(Gravity.BOTTOM, 0, 0)
        toast.show()
    }
}