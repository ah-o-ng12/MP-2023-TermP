package androidtown.org.mp2023termp


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidtown.org.mp2023termp.databinding.ActivityHomeBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*


// 메인페이지 activity
class HomeActivity: AppCompatActivity(){

    private var bind: ActivityHomeBinding? = null
    private val binding get() = bind!!
    private lateinit var auth: FirebaseAuth
    private var doubleBackToExit = false



    var name = ""
    var score = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        bind = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()


        val database = Firebase.database("https://mp-tp-f0d38-default-rtdb.firebaseio.com/")

        val myRef = database.getReference("users")
        //val uid = auth.currentUser!!.uid
        val uid: String? = auth.currentUser?.uid




        if (uid != null) {
            myRef.child(uid).get().addOnSuccessListener{
                name = it.child("name").value.toString().trim()
                score = it.child("score").value.toString().trim()
            }
        }




       // name = intent.getStringExtra("name").toString()
       // score = intent.getStringExtra("score").toString()

        val freeFragment = FreeFragment()
        val wordFragment = WordFragment()
        val quizFragment = QuizFragment()
        val homeFragment = HomeFragment()
        val questionFragment = QuestionFragment()


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

                R.id.question_tab ->{
                    changeFragment(questionFragment)
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

    // fragment 전환 함수
    private fun changeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.container,fragment).commit()
    }

    //뒤로가기로 앱이 종료되는 것을 방지하는 코드
    override fun onBackPressed() {
        if (doubleBackToExit) {
            finishAffinity()
        } else {
            Toast.makeText(this, "종료하시려면 뒤로가기를 한번 더 눌러주세요.", Toast.LENGTH_SHORT).show()
            doubleBackToExit = true
            runDelayed(1500L) {
                doubleBackToExit = false
            }
        }
    }


    //뒤로 가기를 1.5초 이내로 2번 연속 눌러야 종료
    private fun runDelayed(millis: Long, function: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed(function, millis)
    }




}