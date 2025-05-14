package ir.company.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import ir.company.project.databinding.ActivityHomeBinding
import ir.company.project.databinding.ActivityProfileBinding

class homeFragment(
    private val viewPager2: ViewPager2

) : Fragment() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtHomeNext.setOnClickListener{
            viewPager2.currentItem = viewPager2.currentItem + 1
        }

        binding.txtHomePrevious.setOnClickListener{
            viewPager2.currentItem = viewPager2.currentItem - 1
        }
    }
}
