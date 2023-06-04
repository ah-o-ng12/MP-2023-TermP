package androidtown.org.mp2023termp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidtown.org.mp2023termp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth



// 가장 처음 시작하는 activity이자 로그인 페이지
class MainActivity : AppCompatActivity() {

    private var bind: ActivityMainBinding? = null
    private val binding get() = bind!!
    private lateinit var auth: FirebaseAuth
    private var doubleBackToExit = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.goRegisterBtn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)

        } // 회원가입 페이지로 이동

        binding.loginBtn.setOnClickListener {

            val id = binding.lgId.text.toString().trim()
            val pw = binding.lgPass.text.toString().trim()


            auth.signInWithEmailAndPassword(id, pw) // 로그인 실행 코드
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        val intent = Intent(this,HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this, "로그인에 실패 했습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }

        binding.chBtn.setOnClickListener{
            val intent = Intent(this, ChangeActivity::class.java)
            startActivity(intent)
        } // 비밀번호를 잊어 먹었을 시 이용

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