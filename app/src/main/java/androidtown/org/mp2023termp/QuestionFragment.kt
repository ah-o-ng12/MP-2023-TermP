package androidtown.org.mp2023termp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidtown.org.mp2023termp.databinding.FragmentFreeBinding
import androidtown.org.mp2023termp.databinding.FragmentQuestionBinding




class QuestionFragment : Fragment() {

    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionBinding.inflate(inflater, container, false)
        _binding!!.freeBtn.setOnClickListener{
            val intent = Intent(activity, QuestionBoard::class.java)
            startActivity(intent)
        }
        return binding.root
    }



}