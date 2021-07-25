package my.app.examus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnScrollChangedListener
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment


class Fragment3 : Fragment() {
    private lateinit var ScrollView: ScrollView
    private lateinit var MainText: LinearLayout
    private lateinit var MainTextMini: TextView
    private lateinit var MainTextBig: TextView

    private var maxScroll = 0
    private var scrollY = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_e, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { //После создания врагмента идёт инициализация всех нужных объектов
        ScrollView = view.findViewById(R.id.ScrollView)
        MainText = view.findViewById(R.id.miniMain)
        MainTextBig = view.findViewById(R.id.MainText)
        MainTextMini = view.findViewById(R.id.miniMainText)
        val params = MainText.layoutParams as ViewGroup.MarginLayoutParams//Высчитывает максимально возможный скролл для огромного текста
        maxScroll = params.topMargin
    }

    override fun onStart() {
        super.onStart()

        ScrollView.viewTreeObserver.addOnScrollChangedListener(OnScrollChangedListener { //Включается когда производится скролл
            val upDown: Boolean = ScrollView.scrollY < scrollY // true if scroll up
            val params = MainText.layoutParams as ViewGroup.MarginLayoutParams//Параметры LinearLayout MiniMain

            var temp: Int = if (upDown) {//Считаем на сколько нужно подвинуть наш MiniMain
                if (ScrollView.scrollY <= maxScroll) {
                    params.topMargin + (scrollY - ScrollView.scrollY)
                } else
                    0
            } else
                params.topMargin - ScrollView.scrollY + scrollY
            if ((temp < 0) && !(upDown)) {//Двигаем MiniMain в зависимости от прокрутки
                params.topMargin = 0
            } else if ((temp > maxScroll) && (upDown))
                params.topMargin = maxScroll
            else
                params.topMargin = temp

            MainText.layoutParams = params
            scrollY = ScrollView.scrollY

            MainTextBig.alpha=(1-(scrollY.toFloat()*100.0/maxScroll.toFloat()/100.0)/0.5).toFloat()
            MainTextMini.alpha=(scrollY.toFloat()*100.0/maxScroll.toFloat()/100.0).toFloat()
            if(params.topMargin == 0)
                MainText.elevation = 10f
            else
                MainText.elevation = 0f
        })
    }
}