package androidtown.org.mp2023termp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Writing : AppCompatActivity() {
    private lateinit var titleEditText: EditText
    private lateinit var contentEditText: EditText
    private lateinit var buttonUpload: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.writing)

        titleEditText = findViewById(R.id.Title)
        contentEditText = findViewById(R.id.content)

        buttonUpload = findViewById(R.id.Upload)

        buttonUpload.setOnClickListener {
            val title = titleEditText.text.toString()
            val content = contentEditText.text.toString()
            saveToFirebase(title, content)
            finish()
        }
    }

    private fun saveToFirebase(title: String, content: String) {
        val database = Firebase.database
        val reference = database.getReference("post")

        val newPostRef = reference.push()
        newPostRef.child("title").setValue(title)
        newPostRef.child("content").setValue(content)
    }
}