package jp.gr.java_conf.atsushitominaga.fukushimaeventapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.firebase.ui.database.paging.FirebaseDataSource
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity(), ListFragment.OnListFragmentInteractionListener {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val bundle = intent.extras
        var category = bundle?.getString("ButtonCategory") as String

        when(category){
            "WATCH" ->{ listWatchButton.setImageResource(R.drawable.ic_remove_red_eye_black_24dp)
                        watchText.textSize = 14f}
            "FOOD" ->{ listFoodButton.setImageResource(R.drawable.ic_restaurant_menu_black_24dp)
                        foodText.textSize = 14f}
            "SHOPPING" ->{listShoppingButon.setImageResource(R.drawable.ic_shopping_cart_black_24dp)
                        shoppingText.textSize = 14f}
            "JOIN" -> {listJoinButton.setImageResource(R.drawable.ic_accessibility_black_24dp)
                        joinText.textSize = 14f}
        }

        fun resetButtonResource(){
            listWatchButton.setImageResource(R.drawable.ic_remove_red_eye_hai_24dp)
            listFoodButton.setImageResource(R.drawable.ic_restaurant_menu_hai_24dp)
            listShoppingButon.setImageResource(R.drawable.ic_shopping_cart_hai_24dp)
            listJoinButton.setImageResource(R.drawable.ic_accessibility_hai_24dp)
            watchText.textSize = 12f
            foodText.textSize = 12f
            shoppingText.textSize = 12f
            joinText.textSize = 12f
            watchText.setTextColor(Color.GRAY)
            foodText.setTextColor(Color.GRAY)
            shoppingText.setTextColor(Color.GRAY)
            joinText.setTextColor(Color.GRAY)
        }


        supportFragmentManager.beginTransaction().add(R.id.listContener,ListFragment.newInstance(category)).commit()



        listHomeButton.setOnClickListener {
            finish()
        }

        listWatchButton.setOnClickListener {
            if (category != "WATCH"){
                category = "WATCH"
                resetButtonResource()
                listWatchButton.setImageResource(R.drawable.ic_remove_red_eye_black_24dp)
                watchText.textSize = 14f
                watchText.setTextColor(Color.BLACK)
                supportFragmentManager.beginTransaction().replace(R.id.listContener,ListFragment.newInstance(category)).commit()
            }
        }

        listFoodButton.setOnClickListener {
            if (category != "FOOD"){
                category = "FOOD"
                resetButtonResource()
                listFoodButton.setImageResource(R.drawable.ic_restaurant_menu_black_24dp)
                foodText.textSize = 14f
                foodText.setTextColor(Color.BLACK)
                supportFragmentManager.beginTransaction().replace(R.id.listContener,ListFragment.newInstance(category)).commit()
            }
        }

        listShoppingButon.setOnClickListener {
            if (category != "SHOPPING"){
                category = "SHOPPING"
                resetButtonResource()
                listShoppingButon.setImageResource(R.drawable.ic_shopping_cart_black_24dp)
                shoppingText.textSize = 14f
                shoppingText.setTextColor(Color.BLACK)
                supportFragmentManager.beginTransaction().replace(R.id.listContener,ListFragment.newInstance(category)).commit()
            }
        }

        listJoinButton.setOnClickListener {
            if (category != "JOIN"){
                category = "JOIN"
                resetButtonResource()
                joinText.textSize = 14f
                joinText.setTextColor(Color.BLACK)
                listJoinButton.setImageResource(R.drawable.ic_accessibility_black_24dp)
                supportFragmentManager.beginTransaction().replace(R.id.listContener,ListFragment.newInstance(category)).commit()
            }
        }

    }

    override fun onListItemClicked(url: String) {
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("url", url)
        startActivity(intent)
    }


}
