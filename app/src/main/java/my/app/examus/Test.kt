package my.app.examus

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


@Suppress("DEPRECATION")
class Test : AppCompatActivity() {
    private var count = 0
    private var questInt = arrayOfNulls<Int>(20)
    private var doubleclick = false
    private val ArrayInfo = ArrayInfo(1)
    private lateinit var loadpres1: Button
    private lateinit var loadpres2: Button
    private lateinit var loadpres3: Button
    private lateinit var savepres1: Button
    private lateinit var savepres2: Button
    private lateinit var savepres3: Button
    private lateinit var button: Button
    private lateinit var label1: TextView
    private lateinit var textBox1: TextView
    private lateinit var textBox2: TextView
    private lateinit var photobox: ImageView
    private lateinit var settingsrand: LinearLayout
    private lateinit var linearLayout: LinearLayout
    private lateinit var infobar: LinearLayout
    private lateinit var min: SeekBar
    private lateinit var max: SeekBar
    private lateinit var rand: SeekBar
    private lateinit var radioButton1: RadioButton
    private lateinit var radioButton2: RadioButton
    private lateinit var info_: TextView
    private lateinit var min1: EditText
    private lateinit var max1: EditText
    private lateinit var rand1: EditText
    private var mininfo = ""
    private var maxinfo = ""
    private var randinfo = ""
    private var CheckInfo = false

    private var DarkMode: Boolean = false
    private var ShowNum: Boolean = false
    private var APP_PREFERENCES_DARKMODE = "DarkMode"
    private var APP_PRES = "pres1"

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        window.navigationBarColor = resources.getColor(R.color.black)
        setContentView(R.layout.activity_test)
        SwitchToDarkMode()
        ShowNum()
        loadpres1 = findViewById(R.id.load1)
        loadpres2 = findViewById(R.id.load2)
        loadpres3 = findViewById(R.id.load3)
        savepres1 = findViewById(R.id.save1)
        savepres2 = findViewById(R.id.save2)
        savepres3 = findViewById(R.id.save3)
        button = findViewById(R.id.button)
        label1 = findViewById(R.id.label1)
        textBox1 = findViewById(R.id.textBox1)
        textBox2 = findViewById(R.id.textBox2)
        photobox = findViewById(R.id.imageView)
        settingsrand = findViewById(R.id.settingsrand)
        linearLayout = findViewById(R.id.settings)
        infobar = findViewById(R.id.infobar)
        min = findViewById(R.id.min)
        max = findViewById(R.id.max)
        rand = findViewById(R.id.rand)
        radioButton1 = findViewById(R.id.radioButton)
        radioButton2 = findViewById(R.id.radioButton2)
        min1 = findViewById(R.id.min1)
        max1 = findViewById(R.id.max1)
        rand1 = findViewById(R.id.rand1)
        info_ = findViewById(R.id.info)
        createInfoBar()
        rebuildinfo()
        button.setOnTouchListener { _, event ->
            if (event!!.action == MotionEvent.ACTION_DOWN)
                button.alpha = 0.7f
            else if (event.action == MotionEvent.ACTION_UP)
                button.alpha = 1f
            false
        }

        savepres1.setOnClickListener()
        {
            rebuildinfo()
            if (CheckInfo) {
                val prefs = getSharedPreferences("settings", MODE_PRIVATE)
                val editor = prefs.edit()
                val temp =
                    min1.text.toString() + " " + max1.text.toString() + " " + rand1.text.toString()
                APP_PRES = "pres1"
                editor.putString(APP_PRES, temp).apply()
                Toast.makeText(applicationContext, "save 1!", Toast.LENGTH_SHORT).show()
            }
        }

        loadpres1.setOnClickListener() {
            val prefs = getSharedPreferences("settings", MODE_PRIVATE)
            APP_PRES = "pres1"
            val temp = prefs.getString(APP_PRES, "").toString()
            parseString(temp)
            info_.text=""
        }

        savepres2.setOnClickListener()
        {
            rebuildinfo()
            if (CheckInfo) {
                val prefs = getSharedPreferences("settings", MODE_PRIVATE)
                val editor = prefs.edit()
                val temp =
                    min1.text.toString() + " " + max1.text.toString() + " " + rand1.text.toString()
                APP_PRES = "pres2"
                editor.putString(APP_PRES, temp).apply()
                Toast.makeText(applicationContext, "save 2!", Toast.LENGTH_SHORT).show()
            }
        }

        loadpres2.setOnClickListener() {
            val prefs = getSharedPreferences("settings", MODE_PRIVATE)
            APP_PRES = "pres2"
            val temp = prefs.getString(APP_PRES, "").toString()
            parseString(temp)
            info_.text=""
        }

        savepres3.setOnClickListener()
        {
            rebuildinfo()
            if (CheckInfo) {
                val prefs = getSharedPreferences("settings", MODE_PRIVATE)
                val editor = prefs.edit()
                val temp =
                    min1.text.toString() + " " + max1.text.toString() + " " + rand1.text.toString()
                APP_PRES = "pres3"
                editor.putString(APP_PRES, temp).apply()
                Toast.makeText(applicationContext, "save 3!", Toast.LENGTH_SHORT).show()
            }
        }

        loadpres3.setOnClickListener() {
            val prefs = getSharedPreferences("settings", MODE_PRIVATE)
            APP_PRES = "pres3"
            val temp = prefs.getString(APP_PRES, "").toString()
            parseString(temp)
            info_.text=""
        }

        button.setOnClickListener()
        {
            rebuildinfo()
            if (CheckInfo) {
                ButtonClick()
            }
        }

        min.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                // write custom code for progress is changed
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
                mininfo = min.progress.toString()
                min1.setText("")
                min1.append(mininfo)
                rebuildinfo()
            }
        })

        max.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                // write custom code for progress is changed
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
                maxinfo = max.progress.toString()
                max1.setText("")
                max1.append(maxinfo)
                rebuildinfo()
            }
        })

        rand.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                // write custom code for progress is changed
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
                randinfo = seek.progress.toString()
                rand1.setText("")
                rand1.append(randinfo)
                rebuildinfo()
            }
        })

        radioButton1.setOnClickListener() {
            createInfoBar()
            infobar.visibility = View.GONE
            settingsrand.visibility = View.VISIBLE
        }
        radioButton2.setOnClickListener() {
            infobar.visibility = View.VISIBLE
            settingsrand.visibility = View.GONE
        }
    }

    private fun rebuildinfo() {
        info_.text = ""
        checkInfo()
    }

    private fun checkInfo() {
        val _min = min1.text.toString()
        val _max = max1.text.toString()
        val _rand = rand1.text.toString()
        CheckInfo = true

        try {
            _min.toInt()
        } catch (e: NumberFormatException) {
            info_.text =
                "\nОшибка: минимальное число не является числом!"
            CheckInfo = false
            return
        }
        try {
            _max.toInt()
        } catch (e: NumberFormatException) {
            info_.text =
                "\nОшибка: максимальное число не является числом!"
            CheckInfo = false
            return
        }
        try {
            _rand.toInt()
        } catch (e: NumberFormatException) {
            info_.text =
                "\nОшибка: количество (число) не является числом!"
            CheckInfo = false
            return
        }

        if ((_min.toInt() < 1) || (_max.toInt() < 1) || (_rand.toInt() < 1)) {
            info_.text =
                "\nОшибка: любое число не может быть меньше 1!"
            CheckInfo = false
            return
        }
        if ((_min.toInt() > min.max) || (_max.toInt() > min.max)) {
            info_.text =
                "\nОшибка: максимальное и минимальное число не может быть больше максимума : " + min.max.toString()
            CheckInfo = false
            return
        }
        if (_rand.toInt() > 20) {
            info_.text =
                "\nОшибка: количество вопросов не может быть больше 20!"
            CheckInfo = false
            return
        }
        if (_min.toInt() + _rand.toInt() > _max.toInt()) {
            info_.text =
                "\nОшибка: минимальное число не может превышать (максимальное + количество вопросов)!"
            CheckInfo = false
            return
        }
        min.progress = _min.toInt()
        max.progress = _max.toInt()
        rand.progress = _rand.toInt()
    }

    private fun createInfoBar() {
        min.progress = 1
        min.max = ArrayInfo.maxId + 1
        max.max = ArrayInfo.maxId + 1
        max.progress = ArrayInfo.maxId + 1
        rand.progress = 10
        mininfo = min.progress.toString()
        maxinfo = max.progress.toString()
        randinfo = rand.progress.toString()
        min1.setText("")
        min1.append(mininfo)
        max1.setText("")
        max1.append(maxinfo)
        rand1.setText("")
        rand1.append(randinfo)
        textBox2.text =
            "Всего вопросов: " + maxinfo + "\nКоличество рандомных вопросов: " + randinfo
    }

    private fun parseString(input: String) {
        if (input == "")
            return
        mininfo=""
        maxinfo=""
        randinfo=""
        var i = 0
        var count = 0
        while (i < input.length) {
            if (input[i] != ' ') {
                if (count == 0) {
                    mininfo += input[i]
                }
                if (count == 1) {
                    maxinfo += input[i]
                }
                if (count == 2) {
                    randinfo += input[i]
                }
            } else {
                count++
            }
            i++
        }
        min1.setText("")
        min1.append(mininfo)
        max1.setText("")
        max1.append(maxinfo)
        rand1.setText("")
        rand1.append(randinfo)
        min.progress = mininfo.toInt()
        max.progress = maxinfo.toInt()
        rand.progress = randinfo.toInt()
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
        var temp = ((min1.text.toString().toInt() - 1)..(max1.text.toString().toInt() - 1)).random()
        while (temp in questInt)
            temp = ((min1.text.toString().toInt() - 1)..(max1.text.toString().toInt() - 1)).random()
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
        if (count == rand1.text.toString().toInt()) {
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
        if (count == 0) {
            linearLayout.visibility = View.GONE
            settingsrand.visibility = View.VISIBLE
        }
        if (count == rand1.text.toString().toInt()) {
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