package androidtown.org.mp2023termp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.database.*

class SearchFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var database: DatabaseReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        viewPager = requireActivity().findViewById(R.id.vp_viewpager_main)
        searchEditText = view.findViewById(R.id.searchEditText)
        searchButton = view.findViewById(R.id.searchButton)
        database = FirebaseDatabase.getInstance().reference
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchButton.setOnClickListener {
            val searchTerm = searchEditText.text.toString()
            performSearch(searchTerm)
        }
    }

    private fun performSearch(searchTerm: String) {
        val query = database.child("word").orderByChild("Term").equalTo(searchTerm)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val wordId = dataSnapshot.children.firstOrNull()?.key
                    val position = when (wordId) {
                        "1" -> 1 // tab1
                        "2" -> 2 // tab2
                        "3" -> 3 // tab3
                        else -> -1 // 검색 결과 없음
                    }

                    if (position != -1) {
                        viewPager.setCurrentItem(position, true)
                    } else {
                        Toast.makeText(requireContext(), "검색 결과를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "검색 결과를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle the error
            }
        })
    }
}
