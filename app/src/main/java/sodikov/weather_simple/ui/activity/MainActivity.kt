package sodikov.weather_simple.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import sodikov.weather_simple.R
import sodikov.weather_simple.ui.fragments.FragmentMain

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,FragmentMain.newInstance())
            .commit()
    }
}