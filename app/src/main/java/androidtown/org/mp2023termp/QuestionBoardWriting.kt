package androidtown.org.mp2023termp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

class QuestionBoardWriting : AppCompatActivity() {
    private lateinit var titleEditText: EditText
    private lateinit var contentEditText: EditText
    private lateinit var buttonUpload: Button
    private lateinit var buttonIMGUpload: ImageButton
    private lateinit var imageView: ImageView

    private var IMAGE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.writing)

        titleEditText = findViewById(R.id.Title)
        contentEditText = findViewById(R.id.content)

        buttonUpload = findViewById(R.id.Upload)
        buttonIMGUpload = findViewById(R.id.imageButton)
        imageView = findViewById(R.id.imageView)

        buttonUpload.setOnClickListener {
            val title = titleEditText.text.toString()
            val content = contentEditText.text.toString()
            saveToFirebase(title, content)
            finish()
        }

        buttonIMGUpload.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, IMAGE)
        }
    }

    private fun saveToFirebase(title: String, content: String) {
        if (title.isEmpty()) {
            Toast.makeText(this, "제목을 입력하지 않았어요", Toast.LENGTH_SHORT).show()
            return
        }
        if (content.isEmpty()) {
            Toast.makeText(this, "내용을 입력하지 않았어요", Toast.LENGTH_SHORT).show()
            return
        }

        val database = Firebase.database
        val reference = database.getReference("Q_post")
        val storageRef = FirebaseStorage.getInstance().reference

        val newPostRef = reference.push()
        newPostRef.child("title").setValue(title)
        newPostRef.child("content").setValue(content)

        val drawable = imageView.drawable as BitmapDrawable
        val bitmap = drawable.bitmap

        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val imageData = baos.toByteArray()

        var imageFileName = "image_${System.currentTimeMillis()}.jpg"
        val imageRef = storageRef.child("post/$imageFileName")
        val uploadTask = imageRef.putBytes(imageData)

        uploadTask.addOnSuccessListener {taskSnapshot ->
            imageRef.downloadUrl.addOnSuccessListener { uri ->
                newPostRef.child("image").setValue(uri.toString())
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(this, "이미지 업로드 실패: ${exception.message}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IMAGE && resultCode == RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data
            if (selectedImageUri != null) {
                imageView.setImageURI(selectedImageUri)
            }
        }
    }
}
