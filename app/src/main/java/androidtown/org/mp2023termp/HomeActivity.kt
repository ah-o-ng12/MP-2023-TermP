package androidtown.org.mp2023termp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidtown.org.mp2023termp.databinding.ActivityHomeBinding
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth



// 메인페이지 activity
class HomeActivity: AppCompatActivity(){

    private var bind: ActivityHomeBinding? = null
    private val binding get() = bind!!
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        bind = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { // 메뉴 생성
        menuInflater.inflate(R.menu.menu_option, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId // 로그 아웃을 누르면 로그 아웃을 진행
        if(id==R.id.logout){
            auth.signOut()
            Toast.makeText(this, "로그아웃을 합니다",
                Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        if(id==R.id.change_pw){ // 비밀변경 페이지로 이동
            val intent = Intent(this, ChangeActivity::class.java)
            startActivity(intent)
        }

        if(id==R.id.delete_id){ //계정삭제 페이지로 이동
            val intent = Intent(this, DeleteActivity::class.java)
            startActivity(intent)
        }


        return super.onOptionsItemSelected(item)
    }


}