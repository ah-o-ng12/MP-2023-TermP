package androidtown.org.mp2023termp

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.firebase.ktx.Firebase

class BulletinActivity : AppCompatActivity() {
    private lateinit var titleTextView: TextView
    private lateinit var contentTextView: TextView
    private lateinit var imageView: ImageView
    private lateinit var escapeButton: Button
    private lateinit var storageRef: StorageReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bulletin)

        titleTextView  = findViewById(R.id.show_Title)
        contentTextView = findViewById(R.id.show_Content)
        imageView = findViewById(R.id.show_Image)

        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        val image = intent.getStringExtra("image")
        Log.d("BulletinActivity", "Image Path: $image")

        titleTextView.text = title
        contentTextView.text = content

        var storageRef = FirebaseStorage.getInstance().reference
        var imageRef = storageRef.child(image.toString())
        Log.d("BulletinActivity", "image pate: $imageRef")


            Glide.with(this)
                .load(image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)


        escapeButton = findViewById(R.id.escape)

        escapeButton.setOnClickListener {
            finish()
        }
    }
}