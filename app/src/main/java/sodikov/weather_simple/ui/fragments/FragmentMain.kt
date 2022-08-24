package sodikov.weather_simple.ui.fragments

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.tabs.TabLayoutMediator
import org.json.JSONObject
import sodikov.weather_simple.adapters.ViewPagerAdapter
import sodikov.weather_simple.databinding.FragmentMainBinding
import sodikov.weather_simple.model.Weather
import sodikov.weather_simple.ui.activity.MainActivity
import sodikov.weather_simple.utilit.isPermissionGranted
import sodikov.weather_simple.viewModel.MainViewModel

class FragmentMain : Fragment() {
    private lateinit var pLauncher: ActivityResultLauncher<String>
    private var binding: FragmentMainBinding? = null
    private val mBinding get() = binding!!
    private val titleList = arrayListOf("Hours", "Days")
    private val mViewModel : MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return mBinding.root
    }


    override fun onStart() {
        super.onStart()
        tabLayout()
        requestWeatherData("Dushanbe", 1)
        viewModel()
    }

    private fun tabLayout() {
        val adapter = ViewPagerAdapter(this)
        mBinding.viewPager.adapter = adapter
        TabLayoutMediator(mBinding.tabLayout, mBinding.viewPager) { tab, pos ->
            tab.text = titleList[pos]
        }.attach()
    }


    private fun permissionListener() {
        pLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()) {
            Toast.makeText(activity, "Permission is $it", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPermission() {
        if (!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            permissionListener()
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun requestWeatherData(city:String, days:Int){

        val baseURL = "https://api.weatherapi.com/v1/forecast.json"
        val key = "2a835d41ec1c4652bc9123133221908"
        val url = "${baseURL}?key=${key}&q=${city}&days=${days}&aqi=no&alerts=no"

        val queue = Volley.newRequestQueue(context)
        Log.e("MY_TAG", "Weather_URL -> $url")
        val request = StringRequest(
           Request.Method.GET,url,{result ->
               parseWeatherData(result)
           },
           { error ->
               Log.e("MY_TAG", "ERROR_ResponseGetWeather -> $error")
           }
       )
        queue.add(request)
    }

    private fun parseWeatherData(result:String){
        val mainObject = JSONObject(result)
        val weatherObject = Weather(
            mainObject.getJSONObject("location").getString("name"),
            mainObject.getJSONObject("current").getString("last_updated"),
            mainObject.getJSONObject("current").getJSONObject("condition").getString("text"),
            mainObject.getJSONObject("current").getString("temp_c"),
            "","",
            mainObject.getJSONObject("current").getJSONObject("condition").getString("icon"),""
            )

        mViewModel.setData(weatherObject)
    }

    private fun viewModel(){
        mViewModel.weatherData.observe(viewLifecycleOwner){
            mBinding.apply {
                dataAndTimeTV.text = it.time
                cityTV.text = it.city
                currentTempTV.text = it.currentTemp
                conditionTV.text = it.condition
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentMain()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}