package androidtown.org.mp2023termp


import android.os.Bundle
import android.widget.Toast
import androidtown.org.mp2023termp.databinding.ActivityDeleteBinding
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


// 계정삭제 activity
class DeleteActivity: AppCompatActivity() {

    private var bind: ActivityDeleteBinding? = null
    private val binding get() = bind!!
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        bind = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.deleteBtn.setOnClickListener{
            val deleteCode = binding.dtPass.text.toString().trim()
            if(deleteCode=="[삭제하기]") { //정확히 [삭제하기]를 입력했을 시 삭제
                auth.currentUser!!.delete().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        auth.signOut()
                        Toast.makeText(
                            this, "계정이 삭제되었습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                        finishAffinity() // 앱을 완전 종료
                    }
                }
            }else{Toast.makeText(
                this, "다시 입력해주세요.",
                Toast.LENGTH_SHORT
            ).show()}
        }



    }
}