package androidtown.org.mp2023termp


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidtown.org.mp2023termp.databinding.ActivityStendbyBinding
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class StendByActivity: AppCompatActivity (){
    private var bind: ActivityStendbyBinding? = null
    private val binding get() = bind!!
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityStendbyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        val database = Firebase.database("https://mp-tp-f0d38-default-rtdb.firebaseio.com/")
        // uid email name 실시간 데이터베이스에 저장
        val myRef = database.getReference("users")
        val uid = auth.currentUser!!.uid

        var name = ""
        var score = ""

        myRef.child(uid).get().addOnSuccessListener{
            name = it.child("name").value.toString().trim()
            score = it.child("score").value.toString().trim()
        }


        binding.startBtn.setOnClickListener{

            Toast.makeText(this, "환영합니다 "+name+"님 ", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("name",name)
            intent.putExtra("score",score)
            startActivity(intent)
            finish()
        }

    }

}