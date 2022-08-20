package sodikov.weather_simple.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {

    private var _weatherData = MutableLiveData<String>()
    var weatherData:LiveData<String> = _weatherData

    fun getData(){

    }
}