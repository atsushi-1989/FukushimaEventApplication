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
import kotlinx.android.synthetic.main.fragment_news_therd.*

/**
 * A simple [Fragment] subclass.
 */
class NewsTherdFragment : Fragment() {

    private var mListener : OnTherdNewsInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_therd, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference().child("hoge")

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {


                val imageUrl = snapshot.child("url").value.toString()

                val storageRef = FirebaseStorage.getInstance().getReferenceFromUrl(imageUrl)
                storageRef.downloadUrl.addOnCompleteListener { task ->
                    val downloadUrl = task.result
                    Glide.with(newsTherdImageView).load(downloadUrl)
                        .into(newsTherdImageView)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        newsTherdCard.setOnClickListener {
            mListener?.onNewsClicked()
        }
    }

    interface OnTherdNewsInteractionListener{
        fun onNewsClicked()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnTherdNewsInteractionListener){
            mListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

}
