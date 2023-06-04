package androidtown.org.mp2023termp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidtown.org.mp2023termp.databinding.FragmentWordBinding


class WordFragment : Fragment() {


    private var _binding: FragmentWordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWordBinding.inflate(inflater, container, false)
        _binding!!.wordBtn.setOnClickListener{
            val intent = Intent(activity, WordBoard::class.java)
            startActivity(intent)
        }
        return binding.root
    }



}