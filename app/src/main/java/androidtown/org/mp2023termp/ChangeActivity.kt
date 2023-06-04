package androidtown.org.mp2023termp



import android.os.Bundle

import android.widget.Toast
import androidtown.org.mp2023termp.databinding.ActivityChangeBinding
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


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

        val database = Firebase.database("https://mp-tp-f0d38-default-rtdb.firebaseio.com/")
        // uid email name 실시간 데이터베이스에 저장
        val myRef = database.getReference("users")
        val uid = auth.currentUser!!.uid


        var myId = ""

        myRef.child(uid).get().addOnSuccessListener{
            myId = it.child("email").value.toString().trim()

        }

        binding.changeBtn.setOnClickListener{

            val email = binding.cgPass.text.toString().trim()

            if(email == myId){

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
            } else{Toast.makeText(this,"이메일을 다시 확인해주세요",Toast.LENGTH_SHORT).show()}
        }


    }
}