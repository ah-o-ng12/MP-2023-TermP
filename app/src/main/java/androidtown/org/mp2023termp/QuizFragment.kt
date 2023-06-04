package androidtown.org.mp2023termp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidtown.org.mp2023termp.databinding.FragmentQuizBinding
import com.google.firebase.auth.FirebaseAuth


private var _binding: FragmentQuizBinding? = null
private val binding get() = _binding!!
private var userID = ""
private var userScore = ""
private lateinit var auth: FirebaseAuth
class QuizFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        userID = (activity as HomeActivity).name
        userScore = (activity as HomeActivity).score
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)

        binding.quizText.text = userID+"님의 최고점은"
        binding.scoreText.text = userScore+"점"

        binding.quizBtn.setOnClickListener{
            val intent = Intent(activity, QuizBoard::class.java)
            startActivity(intent)
        }
        return binding.root
    }



}