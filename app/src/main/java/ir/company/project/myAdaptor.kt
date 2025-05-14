package ir.company.project

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class myAdaptor(
    private val viewPager2: ViewPager2,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int= 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->ProfileFragment(viewPager2)

            1->homeFragment(viewPager2)

            2->settingsFragment(viewPager2)

            else -> ProfileFragment(viewPager2)

        }
    }


}