package jp.gr.java_conf.atsushitominaga.fukushimaeventapplication

import android.content.AbstractThreadedSyncAdapter
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    NewsFirstFragment.OnFirstNewsInteractionListener,
    NewsSecondFragment.OnSecondNewsInteractionListener,
    NewsTherdFragment.OnTherdNewsInteractionListener{

    var firebaseReference: DatabaseReference? = null
    var layoutManager: LinearLayoutManager? = null

    lateinit var firebaseAdapter: FirebaseRecyclerAdapter<Model,newContentHolder>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseReference = FirebaseDatabase.getInstance().reference!!

        layoutManager = LinearLayoutManager(this)
        newcontentRecyclerView.layoutManager = layoutManager

        pager.adapter = PageAdapter(this)




        val query = firebaseReference!!.child("FOOD")
        val options = FirebaseRecyclerOptions.Builder<Model>().setQuery(query,Model::class.java).build()

        firebaseAdapter = object : FirebaseRecyclerAdapter<Model,newContentHolder>(options) {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newContentHolder {
                val view = LayoutInflater.from(parent!!.context)
                    .inflate(R.layout.new_content, parent, false)
                return newContentHolder(view)
            }

            override fun onBindViewHolder(holder: newContentHolder, position: Int, model: Model) {

                when (model.tagCollor) {
                    1 -> holder.newContentTag.setBackgroundColor(Color.BLUE)
                    2 -> holder.newContentTag.setBackgroundColor(Color.RED)
                    3 -> holder.newContentTag.setBackgroundColor(Color.YELLOW)
                    4 -> holder.newContentTag.setBackgroundColor(Color.GREEN)
                }

                holder.newContentDay.text = model.dateText
                holder.newContentTitle.text = model.titleText
                holder.newContentPlace.text = model.placeText

                val imageUrl = model.imageUrl
                val storageRef = FirebaseStorage.getInstance().getReferenceFromUrl(imageUrl)
                storageRef.downloadUrl.addOnCompleteListener { task ->
                    val downloadUrl = task.result
                    Glide.with(holder.newContentImage.context).load(downloadUrl)
                        .into(holder.newContentImage)
                }

                holder.newContentCard.setOnClickListener {
                    val intent = Intent(applicationContext, DetailActivity::class.java)
                    intent.putExtra("url",model.url)
                    intent.putExtra("detailId", model.detailId)
                    startActivity(intent)
                }
            }
        }
        newcontentRecyclerView.adapter = firebaseAdapter


        watchButton.setOnClickListener {
            val intent = Intent(applicationContext,ListActivity::class.java)
            intent.putExtra("ButtonCategory","WATCH")
            startActivity(intent)
        }

        foodButton.setOnClickListener {
            val intent = Intent(applicationContext,ListActivity::class.java)
            intent.putExtra("ButtonCategory","FOOD")
            startActivity(intent)
        }

        shoppingButon.setOnClickListener {
            val intent = Intent(applicationContext,ListActivity::class.java)
            intent.putExtra("ButtonCategory","SHOPPING")
            startActivity(intent)
        }

        joinButton.setOnClickListener {
            val intent = Intent(applicationContext,ListActivity::class.java)
            intent.putExtra("ButtonCategory","JOIN")
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        firebaseAdapter.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        firebaseAdapter.stopListening()
    }

    override fun onNewsClicked() {
        val intent = Intent(this@MainActivity,DetailActivity::class.java)
        startActivity(intent)
    }




}
