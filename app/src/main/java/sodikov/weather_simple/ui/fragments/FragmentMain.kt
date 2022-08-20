package sodikov.weather_simple.ui.fragments

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import sodikov.weather_simple.adapters.ViewPagerAdapter
import sodikov.weather_simple.databinding.FragmentMainBinding
import sodikov.weather_simple.utilit.isPermissionGranted

class FragmentMain : Fragment() {
    private lateinit var pLauncher: ActivityResultLauncher<String>
    private var binding: FragmentMainBinding? = null
    private val mBinding get() = binding!!
    private val titleList = arrayListOf("Hours", "Days")

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

    companion object {
        @JvmStatic
        fun newInstance() = FragmentMain()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}