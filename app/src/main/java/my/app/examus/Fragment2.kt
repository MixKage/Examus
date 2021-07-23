package my.app.examus

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnScrollChangedListener
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment


class Fragment2 : Fragment() {
    lateinit var ScrollView: ScrollView
    lateinit var MainText: LinearLayout
    lateinit var MainTextMini: TextView
    lateinit var MainTextBig: TextView
    var MarginMainText = 0 //DEL
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_s, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { //После создания врагмента идёт инициализация всех нужных объектов
        ScrollView = view.findViewById(R.id.ScrollView)
        MainText = view.findViewById(R.id.miniMain)
        MainTextBig = view.findViewById(R.id.MainText)
        MainTextMini = view.findViewById(R.id.miniMainText)
//        ScrollView.isVerticalScrollBarEnabled = false
    }

    override fun onStart() {
        super.onStart()
        var scrollY = ScrollView.scrollY
        val params = MainText.layoutParams as ViewGroup.MarginLayoutParams
        var maxScrollMiniMain = 0
        var firstMaxMiniMainScroll = false
        val maxScroll = params.topMargin

        ScrollView.viewTreeObserver.addOnScrollChangedListener(OnScrollChangedListener { //Включается когда производится скролл
            val upDown: Boolean = ScrollView.scrollY < scrollY // true if scroll up
            val params = MainText.layoutParams as ViewGroup.MarginLayoutParams//Параметры LinearLayout MiniMain
            var temp = 0

            temp = if (upDown) {//Считаем на сколько нужно подвинуть наш MiniMain
                if ((ScrollView.scrollY <= maxScrollMiniMain)||maxScrollMiniMain==0) {
                    params.topMargin + (scrollY - ScrollView.scrollY)
                } else
                    0
            } else
                params.topMargin - ScrollView.scrollY + scrollY

            if ((temp < 0) && !(upDown)) {//Высчитываем максимальную прокрутку где нужно приклеивать верхний MiniMain
                params.topMargin = 0
                if (!firstMaxMiniMainScroll) {
                    firstMaxMiniMainScroll = !firstMaxMiniMainScroll
                    maxScrollMiniMain = ScrollView.scrollY
                }
            } else if ((temp > maxScroll) && (upDown))
                params.topMargin = maxScroll
            else
                params.topMargin = temp

            MainText.layoutParams = params
            scrollY = ScrollView.scrollY

            MainTextBig.alpha=1-(scrollY.toFloat()*100.0/maxScroll.toFloat()/100.0).toFloat()
            MainTextMini.alpha=(scrollY.toFloat()*100.0/maxScroll.toFloat()/100.0).toFloat()
//            ScrollView.isVerticalScrollBarEnabled = params.topMargin == 0
            if(params.topMargin == 0)
                MainText.elevation = 10f
            else
                MainText.elevation = 0f
//            Log.d("TAG2", params.topMargin.toString())
//            Log.d("TAG1", MainText.top.toString())
//            Log.d("TAG", ScrollView.scrollY.toString())
        })
//        Log.d("TAG", ScrollView.scrollY.toString())
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG", ScrollView.scrollY.toString())
    }
//    override fun onCreate(){
//        ScrollView = findViewById<Button>(R.id.button1)//ПОЛЬЗОВАТЕЛЬ ДВИГАЕТ СКРОЛЛ
//    }

}