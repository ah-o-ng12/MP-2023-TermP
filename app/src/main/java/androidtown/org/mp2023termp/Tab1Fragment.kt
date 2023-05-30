package androidtown.org.mp2023termp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidtown.org.mp2023termp.databinding.FragmentTab1Binding
import com.google.firebase.database.*
import com.google.firebase.database.FirebaseDatabase

class Tab1Fragment : Fragment() {
    private var _binding: FragmentTab1Binding? = null
    private val binding get() = _binding!!

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = FirebaseDatabase.getInstance().reference
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentTab1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        readDataFromFirebase()
    }

    private fun readDataFromFirebase() {
        val termTextView = binding.WordTerm
        val meaningTextView = binding.WordMeaning

        database.child("word").child("1").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val termValue = dataSnapshot.child("Term").getValue(String::class.java)
                    val meaningValue = dataSnapshot.child("Meaning").getValue(String::class.java)

                    termTextView.text = termValue
                    meaningTextView.text = meaningValue
                } else {
                    Log.d(TAG, "Data does not exist in Firebase")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, "Error retrieving data from Firebase: ${error.message}")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

