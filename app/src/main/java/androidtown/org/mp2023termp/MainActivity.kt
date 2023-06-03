package androidtown.org.mp2023termp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidtown.org.mp2023termp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth



// 가장 처음 시작하는 activity이자 로그인 페이지
class MainActivity : AppCompatActivity() {

    private var bind: ActivityMainBinding? = null
    private val binding get() = bind!!
    private lateinit var auth: FirebaseAuth

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

                        val intent = Intent(this,StendByActivity::class.java)
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
}