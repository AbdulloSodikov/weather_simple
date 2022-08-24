package sodikov.weather_simple.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sodikov.weather_simple.model.Weather

class MainViewModel:ViewModel() {

    private var _weatherData = MutableLiveData<Weather>()
    var weatherData:LiveData<Weather> = _weatherData

    private var _weatherDataList = MutableLiveData<List<Weather>>()
    var weatherDataList:LiveData<List<Weather>> = _weatherDataList

    fun setData(mObject:Weather){
        _weatherData.postValue(mObject)
    }

    fun setListData(list: List<Weather>){
        _weatherDataList.postValue(list)
    }
}