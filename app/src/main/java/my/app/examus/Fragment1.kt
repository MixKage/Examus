package my.app.examus

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment


class Fragment1 : Fragment() {

    private lateinit var ScrollView: ScrollView
    private lateinit var MainText: RelativeLayout
    private lateinit var MainTextMini: TextView
    private lateinit var MainTextBig: TextView
    private lateinit var IconReload: ImageView
    private lateinit var SearchIcon: ImageView

    private var maxScroll = 0
    private var scrollY = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_f, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) { //После создания врагмента идёт инициализация всех нужных объектов
        ScrollView = view.findViewById(R.id.ScrollView)
        MainText = view.findViewById(R.id.miniMain)
        MainTextBig = view.findViewById(R.id.MainText)
        MainTextMini = view.findViewById(R.id.miniMainText)
        IconReload = view.findViewById(R.id.icon_reload)
        SearchIcon = view.findViewById(R.id.searchIcon)

        val params =
            MainText.layoutParams as ViewGroup.MarginLayoutParams//Высчитывает максимально возможный скролл для огромного текста
        maxScroll = params.topMargin

    }

    override fun onStart() {
        super.onStart()
        Log.d("Debug", Resources.getSystem().displayMetrics.widthPixels.toString())
        IconReload.setOnClickListener {
            animateView(IconReload)
        }
        SearchIcon.setOnClickListener {
            animateView(SearchIcon)
        }


        ScrollView.viewTreeObserver.addOnScrollChangedListener { //Включается когда производится скролл
            scrollEngine()
            alphaElevation()
        }
        touchFun()
    }

    override fun onResume() {
        super.onResume()
        scrollEngine()//Скролл
        alphaElevation()//Альфа miniMain
    }

    private fun scrollEngine() {
        val upDown: Boolean = ScrollView.scrollY < scrollY // true if scroll up
        val params =
            MainText.layoutParams as ViewGroup.MarginLayoutParams//Параметры LinearLayout MiniMain

        val temp: Int = if (upDown) {//Считаем на сколько нужно подвинуть наш MiniMain
            if (ScrollView.scrollY <= maxScroll) {
                params.topMargin + (scrollY - ScrollView.scrollY)
            } else
                0
        } else
            params.topMargin - ScrollView.scrollY + scrollY
        if ((temp < 0) && !(upDown)) {//Двигаем MiniMain в зависимости от прокрутки
            params.topMargin = 0
        } else if ((temp > maxScroll) && (upDown)) {
            params.topMargin = maxScroll
        } else {
            params.topMargin = temp
        }

        MainText.layoutParams = params
        scrollY = ScrollView.scrollY
    }

    private fun animateView(view: ImageView) {
        when (val drawable = view.drawable) {
//        is AnimatedVectorDrawableCompat -> {
//            drawable.start()
//        }
            is AnimatedVectorDrawable -> {
                drawable.start()
            }
        }
    }

    private fun threadTimer() {
        var lastScrollY = -21
        Thread {
            while (ScrollView.scaleY.toInt() != lastScrollY) {
                lastScrollY = ScrollView.scrollY
                Thread.sleep(500)
                if (ScrollView.scaleY.toInt() != lastScrollY)
                    lastScrollY = ScrollView.scaleY.toInt()
            }
            if (scrollY < maxScroll) {
                if (scrollY >= maxScroll / 3)//Плавный скролл
                    ScrollView.post { ScrollView.smoothScrollTo(0, maxScroll) }
                else if (scrollY < maxScroll / 3)
                    ScrollView.post { ScrollView.smoothScrollTo(0, 0) }
            }
        }.start()
    }//Через секунду проверяет состояние Scroll для магнита

    private fun alphaElevation() {
        val params =
            MainText.layoutParams as ViewGroup.MarginLayoutParams
        MainTextBig.alpha =
            (1 - (scrollY.toFloat() * 100.0 / maxScroll.toFloat() / 100.0) / 0.5).toFloat()
        MainTextMini.alpha = (scrollY.toFloat() * 100.0 / maxScroll.toFloat() / 100.0).toFloat()
        if (params.topMargin == 0)
            MainText.elevation = 10f
        else
            MainText.elevation = 0.1f
    }//Изменяет alpha и elevation MainText

    @SuppressLint("ClickableViewAccessibility")
    fun touchFun() {
        ScrollView.setOnTouchListener { v, event -> // ... Respond to touch events
            /*
            return true if the listener has consumed the event, false otherwise.
             */
            val action = event.action
            when (action) {
                MotionEvent.ACTION_DOWN -> {
                    /*
                    Beware of creating a listener that returns false
                    for the ACTION_DOWN event.
                    If you do this, the listener will not be called
                    for the subsequent ACTION_MOVE and ACTION_UP string of events.
                    This is because ACTION_DOWN is the starting point for all touch events.
                     */
                    IconReload.setOnClickListener {
                        animateView(IconReload)
                    }
                    SearchIcon.setOnClickListener {
                        animateView(SearchIcon)
                    }
                    Log.d("GG", "Frag1- DOWN")
                    false
                }

                MotionEvent.ACTION_UP -> {
                    threadTimer()
                    Log.d("GG", "Frag1- UP")
                    false
                }
                MotionEvent.ACTION_CANCEL -> {
                    false
                }
                MotionEvent.ACTION_OUTSIDE -> {
                    false
                }
                else -> false
            }
        }
    }//Проверка на нажатие на экран

}
