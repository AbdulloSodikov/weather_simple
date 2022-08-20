package sodikov.weather_simple.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import sodikov.weather_simple.ui.fragments.DaysFragment
import sodikov.weather_simple.ui.fragments.HoursFragment

class ViewPagerAdapter (fragment: Fragment): FragmentStateAdapter (fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> HoursFragment.newInstance()
            1 -> DaysFragment.newInstance()
            else -> HoursFragment.newInstance()
        }
    }
}