package androidtown.org.mp2023termp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class BulletinActivity : AppCompatActivity() {
    private lateinit var titleTextView: TextView
    private lateinit var contentTextView: TextView
    private lateinit var escapeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bulletin)

        titleTextView  = findViewById(R.id.show_Title)
        contentTextView = findViewById(R.id.show_Content)

        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")

        titleTextView.text = title
        contentTextView.text = content

        escapeButton = findViewById(R.id.escape)

        escapeButton.setOnClickListener {
            finish()
        }
    }
}