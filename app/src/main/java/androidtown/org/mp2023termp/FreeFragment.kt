package androidtown.org.mp2023termp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidtown.org.mp2023termp.databinding.FragmentFreeBinding

class FreeFragment : Fragment() {

    private var _binding: FragmentFreeBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentFreeBinding.inflate(inflater, container, false)
        _binding!!.freeBtn.setOnClickListener{
            val intent = Intent(activity, FreeBulletinBoard::class.java)
            startActivity(intent)
        }
        return binding.root

    }



}