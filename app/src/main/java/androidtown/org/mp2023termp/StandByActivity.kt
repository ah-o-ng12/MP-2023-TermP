package androidtown.org.mp2023termp


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidtown.org.mp2023termp.databinding.ActivityStandbyBinding
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*


class StandByActivity: AppCompatActivity (){
    private var bind: ActivityStandbyBinding? = null
    private val binding get() = bind!!
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityStandbyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        val database = Firebase.database("https://mp-tp-f0d38-default-rtdb.firebaseio.com/")

        val myRef = database.getReference("users")
        val uid = auth.currentUser!!.uid

        var name = ""
        var score = ""


        myRef.child(uid).get().addOnSuccessListener{
            name = it.child("name").value.toString().trim()
            score = it.child("score").value.toString().trim()
        }



        // 데이터베이스에서 데이터들을 가져온다.



        binding.startBtn.setOnClickListener{

            Toast.makeText(this, "환영합니다 "+name+"님 ", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("name",name)
            intent.putExtra("score",score) // 데이터베이스에서 가져온 유저데이터를 홈화면에 넘긴다.
            startActivity(intent)
            finish()
        }

    }





}