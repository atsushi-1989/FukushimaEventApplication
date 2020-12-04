package jp.gr.java_conf.atsushitominaga.fukushimaeventapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_news_second.*

/**
 * A simple [Fragment] subclass.
 */
class NewsSecondFragment : Fragment() {

    private var mListener : OnSecondNewsInteractionListener? = null

    private var detailId : String = ""
    private var url : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_second, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference().child("TOP").child("image2")

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                url = snapshot.child("url").value.toString()
                detailId = snapshot.child("detailId").value.toString()


                val imageUrl = snapshot.child("imageUrl").value.toString()
                val storageRef = FirebaseStorage.getInstance().getReferenceFromUrl(imageUrl)
                storageRef.downloadUrl.addOnCompleteListener { task ->
                    val downloadUrl = task.result
                    Glide.with(newsSecondImageView).load(downloadUrl)
                        .into(newsSecondImageView)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        newsSecondCard.setOnClickListener {
            mListener?.onNewsClicked(url,detailId)
        }
    }

    interface OnSecondNewsInteractionListener{
        fun onNewsClicked(url: String, detailId: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSecondNewsInteractionListener){
            mListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

}
