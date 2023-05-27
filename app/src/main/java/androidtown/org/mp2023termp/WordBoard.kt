package androidtown.org.mp2023termp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidtown.org.mp2023termp.databinding.WordBoardBinding
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator

class WordBoard : AppCompatActivity() {
    lateinit var binding: WordBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WordBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewPager()
    }

    private fun initViewPager() {
        //ViewPager2 Adapter 셋팅
        var viewPager2Adatper = ViewPager2Adapter(this)
        viewPager2Adatper.addFragment(Tab1Fragment())
        viewPager2Adatper.addFragment(Tab2Fragment())
        viewPager2Adatper.addFragment(Tab3Fragment())

        //Adapter 연결
        binding.vpViewpagerMain.apply {
            adapter = viewPager2Adatper

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }
            })
        }

        //ViewPager, TabLayout 연결
        TabLayoutMediator(binding.tlNavigationView, binding.vpViewpagerMain) { tab, position ->
            Log.e("YMC", "ViewPager position: ${position}")
            when (position) {
                0 -> tab.text = "1"
                1 -> tab.text = "2"
                2 -> tab.text = "3"
            }
        }.attach()
    }
}