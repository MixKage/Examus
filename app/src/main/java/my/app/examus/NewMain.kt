package my.app.examus

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager

private var CurrentFragment: Int = 1
private lateinit var downloadicon: ImageView
private lateinit var mainicon: ImageView
private lateinit var settingicon: ImageView

private fun alfaAnimIcons(CurrentFragment: Int) {
    downloadicon.alpha = 0.6f
    mainicon.alpha = 0.6f
    settingicon.alpha = 0.6f
    when (CurrentFragment) {
        0 -> {
            downloadicon.alpha = 1f
            animateView(downloadicon)
        }
        1 -> {
            mainicon.alpha = 1f
            animateView(mainicon)
        }
        else -> {
            settingicon.alpha = 1f
            animateView(settingicon)
        }
    }
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

class NewMain : AppCompatActivity() {
    lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        window.navigationBarColor = resources.getColor(R.color.black)
        setContentView(R.layout.activity_new_main)
        downloadicon = findViewById(R.id.downloadicon)
        mainicon = findViewById(R.id.mainicon)
        settingicon = findViewById(R.id.settingicon)
        val adapter: NewMain.MyAdapter = NewMain.MyAdapter(supportFragmentManager)
        viewPager = findViewById(R.id.viewpager)
        viewPager.adapter = adapter // устанавливаем адаптер
        viewPager.currentItem = 1 // выводим второй экран
        alfaAnimIcons(1)

        downloadicon.setOnClickListener {
            viewPager.currentItem = 0
            CurrentFragment = 0
            alfaAnimIcons(CurrentFragment)
        }
        mainicon.setOnClickListener {
            viewPager.currentItem = 1
            CurrentFragment = 1
            alfaAnimIcons(CurrentFragment)
        }
        settingicon.setOnClickListener {
            viewPager.currentItem = 2
            CurrentFragment = 2
            alfaAnimIcons(CurrentFragment)
        }
    }

    class MyAdapter internal constructor(fm: FragmentManager) :
        FragmentPagerAdapter(fm) {
        override fun getCount(): Int {
            return 3
        }

        override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
            if (CurrentFragment != position) {
                alfaAnimIcons(position)
                CurrentFragment = position
                super.setPrimaryItem(container, position, `object`)
            }
        }

        override fun getItem(position: Int): Fragment {
            CurrentFragment = position
            alfaAnimIcons(CurrentFragment)
            return when (position) {
                0 -> Fragment1()
                1 -> Fragment2()
                2 -> Fragment3()
                else -> Fragment1()
            }
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        //This is used to hide/show 'Status Bar' & 'System Bar'. Swip bar to get it as visible.
        val decorView: View = window.decorView
        if (hasFocus) {
            decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            )
        }
    }
}