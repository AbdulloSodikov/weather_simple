package sodikov.weather_simple.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import sodikov.weather_simple.databinding.FragmentMainBinding

class FragmentMain : Fragment() {
    private var binding: FragmentMainBinding? = null
    private val mBinding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return mBinding.root
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