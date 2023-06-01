package androidtown.org.mp2023termp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidtown.org.mp2023termp.databinding.QuestionResultBinding
import androidtown.org.mp2023termp.databinding.QuizBoardBinding

class QuestionResult : AppCompatActivity() {

    private lateinit var binding: QuestionResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = QuestionResultBinding.inflate(layoutInflater)
        setContentView(R.layout.question_result)

        val score = intent.getIntExtra("score", 0)
        val totalSize = intent.getIntExtra("totalSize", 0)

        //점수 보여주기
        binding.scoreText.text = getString(R.string.count_label, score, totalSize)

        //다시하기 버튼
        binding.resetBtn.setOnClickListener {
            val intent =  Intent(this@QuestionResult, QuizBoard::class.java)
            startActivity(intent)
        }
    }
}