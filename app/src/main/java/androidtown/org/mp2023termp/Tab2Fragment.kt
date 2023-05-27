package androidtown.org.mp2023termp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidtown.org.mp2023termp.databinding.FragmentTab2Binding

class Tab2Fragment : Fragment() {
    private var _binding: FragmentTab2Binding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        _binding = FragmentTab2Binding.inflate(inflater, container, false)
        return binding.root
    }
}