package my.app.examus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class SplashScreen : AppCompatActivity() {
    private var DarkMode: Boolean = false
    private var APP_PREFERENCES_DARKMODE = "DarkMode"
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_OSexamen)
        window.navigationBarColor = resources.getColor(R.color.black)
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        SwitchToDarkModeApp()
        setContentView(R.layout.activity_splash_screen)
        SwitchToDarkMode()
        startActivity(Intent(this, Tutor::class.java))
        finish()
//        val SPLASH_TIME_OUT:Long = 1000 // 1 sec
//        Handler().postDelayed({
//            // This method will be executed once the timer is over
//            // Start your app main activity
//
//
//            // close this activity
//
//        }, SPLASH_TIME_OUT)
    }

    private fun SwitchToDarkModeApp() {
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        DarkMode = prefs.getBoolean(APP_PREFERENCES_DARKMODE, false)
        if (!DarkMode)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    private fun SwitchToDarkMode() {
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        DarkMode = prefs.getBoolean(APP_PREFERENCES_DARKMODE, false)
        if (!DarkMode) {
            //TODO ADD FUNC
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}