package androidtown.org.mp2023termp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidtown.org.mp2023termp.databinding.QuestionResultBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class QuestionResult : AppCompatActivity() {

    private lateinit var binding: QuestionResultBinding
    private lateinit var auth: FirebaseAuth

    private var bestScore:Int = 0
    private var scoreData = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = QuestionResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()


        val database = Firebase.database("https://mp-tp-f0d38-default-rtdb.firebaseio.com/")
        // uid email name 실시간 데이터베이스에 저장
        val myRef = database.getReference("users")
        val uid = auth.currentUser!!.uid

        myRef.child(uid).get().addOnSuccessListener{
            scoreData = it.child("score").value.toString().trim()
            bestScore= Integer.parseInt(scoreData)
        }

        val score = intent.getIntExtra("score", 0)
        val totalSize = intent.getIntExtra("totalSize", 0)


        //점수 보여주기
        binding.scoreText.text = getString(R.string.count_label, score, totalSize)

        //다시하기 버튼
        binding.resetBtn.setOnClickListener {

            if(score>bestScore){
                myRef.child(uid).child("score").setValue(score)
                Toast.makeText(this,"최고기록 갱신 성공",Toast.LENGTH_SHORT).show()
            }else{Toast.makeText(this,"최고기록 갱신 실패",Toast.LENGTH_SHORT).show()}

            val intent =  Intent(this@QuestionResult, QuizBoard::class.java)
            startActivity(intent)
            finish()
        }

        binding.finishBtn.setOnClickListener{
            if(score>bestScore){
                myRef.child(uid).child("score").setValue(score)
                Toast.makeText(this,"최고기록 갱신 성공",Toast.LENGTH_SHORT).show()

                val intent =  Intent(this@QuestionResult, HomeActivity::class.java)
                startActivity(intent)
            }else{ Toast.makeText(this,"최고기록 갱신 실패",Toast.LENGTH_SHORT).show()}

            finish()

        }

    }


}