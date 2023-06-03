package androidtown.org.mp2023termp


import android.os.Bundle
import android.widget.Toast
import androidtown.org.mp2023termp.databinding.ActivityRegisterBinding
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


// 회원가입을 하는 activity
class RegisterActivity: AppCompatActivity() {
    private var bind: ActivityRegisterBinding? = null
    private val binding get() = bind!!
    private lateinit var auth: FirebaseAuth

    var hashMap: HashMap<Any, String> = HashMap()


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        bind = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.registerBtn.setOnClickListener{
            val email = binding.rgId.text.toString().trim()
            val pw = binding.rgPass.text.toString().trim()
            val pw2 = binding.rgPass2.text.toString().trim()
            val name = binding.rgName.text.toString().trim()

            if(name==""|| pw==""|| pw2==""|| email==""){ //빈칸이 있을 시

                Toast.makeText(this, "빈칸을 채워주세요",
                    Toast.LENGTH_SHORT).show()
            }

            else{
                if(!email.contains("@gachon.ac.kr")){ //가천아이디가 아닐 경우
                    Toast.makeText(this, "가천아이디를 입력해주세요",
                        Toast.LENGTH_SHORT).show()
                }

                else if(pw!=pw2){ // 비밀번호 재입력이 틀릴 경우
                    Toast.makeText(this, "비밀번호를 확인해주세요",
                        Toast.LENGTH_SHORT).show()
                }

                else{
                    auth.createUserWithEmailAndPassword(email,pw) // 회원가입 진행
                        .addOnCompleteListener(this){task ->
                            binding.rgId.text.clear()
                            binding.rgPass.text.clear()
                            binding.rgPass2.text.clear()
                            binding.rgName.text.clear()

                            if(task.isSuccessful){ // 성공했을 시
                                auth.currentUser?.sendEmailVerification() //가천아이디로 인증메일 전송
                                    ?.addOnCompleteListener{ sendTask ->
                                    if(sendTask.isSuccessful){
                                        Toast.makeText(this, "인증 이메일 전송 완료",
                                            Toast.LENGTH_SHORT).show()


                                        val uid = auth.currentUser!!.uid


                                        hashMap["email"] = email
                                        hashMap["name"] = name

                                        val database = Firebase.database("https://mp-tp-f0d38-default-rtdb.firebaseio.com/")
                                        // uid email name 실시간 데이터베이스에 저장
                                        val myRef = database.getReference("users")
                                        myRef.child(uid).setValue(hashMap)
                                        myRef.child(uid).child("score").setValue(0)


                                        finish() // 로그인 페이지로 이동

                                    }else{Toast.makeText(this, "인증 실패",
                                       Toast.LENGTH_SHORT).show()}

                                    }
                            }else{Toast.makeText(this, "회원가입 실패",
                                Toast.LENGTH_SHORT).show()}
                        }
                }
            }

        }

    }


}

