package androidtown.org.mp2023termp


import android.os.Bundle
import android.widget.Toast
import androidtown.org.mp2023termp.databinding.ActivityChangeBinding
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


// 비밀번호 변경 페이지
class ChangeActivity: AppCompatActivity(){
    private var bind: ActivityChangeBinding? = null
    private val binding get() = bind!!
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        bind = ActivityChangeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.changeBtn.setOnClickListener{
            // 버튼을 누르면 입력한 email로 비번 변경 전송 메일을 보냄
            // 수정 요청: 데이터베이스에 저장된 email과 대조해서 입력한 email이 같을 경우만
            // 인증메일을 보내도록 조건문 삽입 요망.
            val email = binding.cgPass.text.toString().trim()
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener{task ->
                    if(task.isSuccessful){
                        auth.signOut()
                        Toast.makeText(this, "변경된 비밀번호로 로그인하세요",
                            Toast.LENGTH_SHORT).show()

                        finishAffinity() // 앱을 완전 종료
                    } else{
                        Toast.makeText(this, "이메일을 다시 확인해주세요",
                            Toast.LENGTH_SHORT).show()
                    }

                }
        }


    }
}