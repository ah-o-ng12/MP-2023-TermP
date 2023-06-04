package androidtown.org.mp2023termp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.firebase.database.*

class QuestionBoard : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var dataRef: DatabaseReference
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.question_board)

        database = FirebaseDatabase.getInstance()
        dataRef = database.getReference("Q_post")

        var recyclerview: RecyclerView = findViewById(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerViewAdapter = RecyclerViewAdapter()
        recyclerview.adapter = recyclerViewAdapter

        val writingButton: Button = findViewById(R.id.Writing)
        writingButton.setOnClickListener {
            val intent = Intent(this,QuestionBoardWriting::class.java)
            startActivity(intent)
        }
    }

    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        private var bulletins: MutableList<Post> = mutableListOf()
        private var valueEventListener: ValueEventListener? = null

        init {
            valueEventListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    bulletins.clear()
                    for (postSnapshot in snapshot.children) {
                        val bulletin = postSnapshot.getValue(Post::class.java)
                        bulletin?.let {
                            bulletins.add(it)
                        }
                    }
                    notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@QuestionBoard,"에러",Toast.LENGTH_LONG).show()
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.bulletin, parent, false)
            return ViewHolder(view)
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val titleTextView: TextView = view.findViewById(R.id.title)
            val imageView: ImageView = view.findViewById(R.id.imageView)

            init {
                itemView.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val bulletin = bulletins[position]
                        val context = itemView.context
                        val intent = Intent(context, BulletinActivity::class.java).apply {
                            putExtra("title", bulletin.title)
                            putExtra("content", bulletin.content)
                            putExtra("image", bulletin.image)
                        }
                        context.startActivity(intent)
                    }
                }
            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val viewHolder = holder as ViewHolder
            viewHolder.titleTextView.text = bulletins[position].title

            var imageUrl = bulletins[position].image
            if (imageUrl != null) {
                Glide.with(holder.itemView.context)
                    .load(imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(viewHolder.imageView)
            }
        }

        override fun getItemCount(): Int {
            return bulletins.size
        }

        override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
            super.onAttachedToRecyclerView(recyclerView)
            dataRef.addValueEventListener(valueEventListener as ValueEventListener)
        }

        override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
            super.onDetachedFromRecyclerView(recyclerView)
            dataRef.removeEventListener(valueEventListener as ValueEventListener)
        }
    }
}
