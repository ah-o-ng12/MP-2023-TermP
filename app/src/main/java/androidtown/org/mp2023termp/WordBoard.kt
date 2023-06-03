package androidtown.org.mp2023termp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidtown.org.mp2023termp.databinding.WordBoardBinding
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class WordBoard : AppCompatActivity() {
    lateinit var binding: WordBoardBinding

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WordBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Firebase.database.reference // Firebase 실시간 데이터베이스 초기화

        initViewPager()
    }

    private fun initViewPager() {
        //ViewPager2 Adapter 셋팅
        var viewPager2Adapter = ViewPager2Adapter(this)
        viewPager2Adapter.addFragment(SearchFragment()) // 검색 탭 추가
        viewPager2Adapter.addFragment(Tab1Fragment())
        viewPager2Adapter.addFragment(Tab2Fragment())
        viewPager2Adapter.addFragment(Tab3Fragment())

        //Adapter 연결
        binding.vpViewpagerMain.apply {
            adapter = viewPager2Adapter

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
                0 -> tab.text = "Search" // 검색 탭
                1 -> tab.text = "1"
                2 -> tab.text = "2"
                3 -> tab.text = "3"
            }
        }.attach()
    }

}