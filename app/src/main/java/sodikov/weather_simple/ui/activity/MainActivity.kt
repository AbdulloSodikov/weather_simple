package sodikov.weather_simple.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import sodikov.weather_simple.R
import sodikov.weather_simple.ui.fragments.FragmentMain
import sodikov.weather_simple.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    val mViewModel : MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,FragmentMain.newInstance())
            .commit()
    }
}