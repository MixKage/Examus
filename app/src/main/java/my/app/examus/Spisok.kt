package my.app.examus

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding


@Suppress("DEPRECATION")
class Spisok : AppCompatActivity() {
    val ArrayInfo = ArrayInfo()
    private var DarkMode: Boolean = false
    private var ShowNum: Boolean = false
    private var APP_PREFERENCES_DARKMODE = "DarkMode"

    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        window.navigationBarColor = resources.getColor(R.color.black)
        setContentView(R.layout.activity_spisok)
        SwitchToDarkMode()
        ShowNum()
        val arguments = intent.extras
        val mode = arguments!!["mode"].toString()
        ArrayInfo.generateFromMode(mode.toInt())
        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout)

        var CountID = 0
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        DarkMode = prefs.getBoolean(APP_PREFERENCES_DARKMODE, false)
        while (CountID <= ArrayInfo.getMaxId()) {
            val b = Button(applicationContext)
            if (!ShowNum)
                b.text = " " + ArrayInfo.getQuestion(CountID)//quest[CountID]
            else
                b.text = (CountID + 1).toString() + ". " + ArrayInfo.getQuestion(CountID)
            b.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            b.id = CountID + 1
            b.setPadding(5)
            b.text
            if (!DarkMode) {
                b.setBackgroundResource(R.drawable.shadow)
                b.setTextColor(resources.getColor(R.color.cardview_dark_background))
            } else {
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
        button0?.setOnTouchListener { _, event ->
            if (event!!.action == MotionEvent.ACTION_DOWN)
                button0.alpha = 0.7f
            else if (event.action == MotionEvent.ACTION_UP)
                button0.alpha = 1f
            false
        }
        button0?.setOnClickListener { ExitToMenu() }
    }

    fun ShowNum() {
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        APP_PREFERENCES_DARKMODE = "ShowNum"
        ShowNum = prefs.getBoolean(APP_PREFERENCES_DARKMODE, false)
        APP_PREFERENCES_DARKMODE = "DarkMode"
    }

    @Suppress("DEPRECATION")
    private fun SwitchToDarkMode() {
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        val back = findViewById<LinearLayout>(R.id.back)
        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout1)
        val maintext = findViewById<TextView>(R.id.label)
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

    fun SeeQuest(a: Int) {
        val showQueste = Intent(this, ShowQuest::class.java)
        showQueste.putExtra("quest", ArrayInfo.getQuestion(a - 1))
        showQueste.putExtra("answer", ArrayInfo.getAnswer(a - 1))
        startActivity(showQueste)
    }

    fun ExitToMenu() {
        this.finish()
    }
}
