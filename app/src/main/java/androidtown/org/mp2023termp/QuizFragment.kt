package androidtown.org.mp2023termp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidtown.org.mp2023termp.databinding.FragmentQuizBinding



private var _binding: FragmentQuizBinding? = null
private val binding get() = _binding!!

class QuizFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        _binding!!.quizBtn.setOnClickListener{
            val intent = Intent(activity, QuizBoard::class.java)
            startActivity(intent)
        }
        return binding.root
    }



}