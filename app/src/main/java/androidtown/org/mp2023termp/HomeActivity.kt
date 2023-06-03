package androidtown.org.mp2023termp


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidtown.org.mp2023termp.databinding.ActivityHomeBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth


// 메인페이지 activity
class HomeActivity: AppCompatActivity(){

    private var bind: ActivityHomeBinding? = null
    private val binding get() = bind!!
    private lateinit var auth: FirebaseAuth

    var name = ""
    var score = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        bind = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        name = intent.getStringExtra("name").toString()
        score = intent.getStringExtra("score").toString()

        Toast.makeText(this, "환영합니다 "+name+"님 ",Toast.LENGTH_SHORT).show()

        val freeFragment = FreeFragment()
        val wordFragment = WordFragment()
        val quizFragment = QuizFragment()
        val homeFragment = HomeFragment()

        changeFragment(homeFragment)

        binding.bottomMenu.setOnItemSelectedListener{ item ->
            when(item.itemId){
                R.id.free_tab ->{
                    changeFragment(freeFragment)
                }

                R.id.word_tab ->{
                    changeFragment(wordFragment)
                }

                R.id.quiz_tab ->{
                    changeFragment(quizFragment)
                }

                R.id.home_tab ->{
                    changeFragment(homeFragment)
                }

            }

            true

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { // 메뉴 생성
        menuInflater.inflate(R.menu.menu_option, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //상단 옵션 메뉴

        val id = item.itemId // 로그 아웃을 누르면 로그 아웃을 진행
        if(id==R.id.logout){
            auth.signOut()
            Toast.makeText(this, "로그아웃을 합니다",
                Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        if(id==R.id.change_pw){ // 비밀변경 페이지로 이동
            val intent = Intent(this, ChangeActivity::class.java)
            startActivity(intent)
        }

        if(id==R.id.delete_id){ //계정삭제 페이지로 이동
            val intent = Intent(this, DeleteActivity::class.java)
            startActivity(intent)
        }


        return super.onOptionsItemSelected(item)
    }

    private fun changeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.container,fragment).commit()
    }




}