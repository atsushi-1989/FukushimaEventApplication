package jp.gr.java_conf.atsushitominaga.fukushimaeventapplication

import android.content.Context
import android.graphics.SurfaceTexture
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
import kotlinx.android.synthetic.main.fragment_news_first.*

/**
 * A simple [Fragment] subclass.
 */




class dtailFragment : Fragment() {

    private var detailId : String = ""

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
        val ref = database.getReference().child("DETAIL").child(detailId)

        ref.addValueEventListener(object :ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                val text = snapshot.child("titleText").value.toString()
                detailTitleTextView.text = text


                val imageUrl = snapshot.child("coverImage").value.toString()

//                val storageRef = FirebaseStorage.getInstance().getReferenceFromUrl(imageUrl)
//                storageRef.downloadUrl.addOnCompleteListener { task ->
//                    val downloadUrl = task.result
//                    Glide.with(detailCoverImageView).load(downloadUrl)
//                        .into(detailCoverImageView)
//                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    companion object{

        fun newInstance(detailId : String) : dtailFragment{
            val fragment = dtailFragment()
            val args = Bundle()
            args.putString("ARG_detailId", detailId)
            return fragment
        }
    }




}
