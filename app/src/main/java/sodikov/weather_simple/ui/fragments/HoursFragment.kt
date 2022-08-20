package sodikov.weather_simple.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import sodikov.weather_simple.adapters.WeatherAdapter
import sodikov.weather_simple.databinding.FragmentHoursBinding
import sodikov.weather_simple.model.Weather

class HoursFragment : Fragment() {
    private var binding: FragmentHoursBinding? = null
    private val mBinding get() = binding!!
    private lateinit var adapter: WeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHoursBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() = with(mBinding) {
        adapter = WeatherAdapter()
        weatherHoursRV.adapter = adapter
        val list = arrayListOf<Weather>()
        repeat(10){index ->
            list.add(Weather("","","Sunny","${30+index}°C","","",""))
        }
        adapter.submitList(list)
    }


    companion object {
        @JvmStatic
        fun newInstance() = HoursFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}