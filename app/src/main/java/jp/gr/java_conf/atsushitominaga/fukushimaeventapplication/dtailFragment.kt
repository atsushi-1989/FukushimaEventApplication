package jp.gr.java_conf.atsushitominaga.fukushimaeventapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_dtail.*

/**
 * A simple [Fragment] subclass.
 */
class dtailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dtail, container, false)



    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference().child("hoge")

        ref.addValueEventListener(object :ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                val text = snapshot.child("text").value.toString()
                textView9.text = text

                val imageUrl = snapshot.child("url").value.toString()

                val storageRef = FirebaseStorage.getInstance().getReferenceFromUrl(imageUrl)
                storageRef.downloadUrl.addOnCompleteListener { task ->
                    val downloadUrl = task.result
                    Glide.with(detailImageView).load(downloadUrl)
                        .into(detailImageView)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })




    }

}
