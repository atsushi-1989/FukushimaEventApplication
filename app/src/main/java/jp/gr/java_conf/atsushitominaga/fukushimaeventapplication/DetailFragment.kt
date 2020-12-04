package jp.gr.java_conf.atsushitominaga.fukushimaeventapplication

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
import kotlinx.android.synthetic.main.fragment_detail.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference().child("DETAIL").child(param1!!)

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val Title = snapshot.child("titleText").value.toString()
                detailTitleTextView.text = Title

                val coverimage = snapshot.child("coverImage").value.toString()
                val coverImageUrl= FirebaseStorage.getInstance().getReferenceFromUrl(coverimage)
                coverImageUrl.downloadUrl.addOnCompleteListener { task ->
                    val downloadUrl = task.result
                    Glide.with(detailCoverImageView).load(downloadUrl)
                        .into(detailCoverImageView)
                }

                val content1image = snapshot.child("content1image").value.toString()
                val content1imageUrl = FirebaseStorage.getInstance().getReferenceFromUrl(content1image)
                content1imageUrl.downloadUrl.addOnCompleteListener { task ->
                    val downloadUrl = task.result
                    Glide.with(detailContent1ImageView).load(downloadUrl)
                        .into(detailContent1ImageView)
                }

                val content1text = snapshot.child("content1text").value.toString()
                detailContent1TextView.text = content1text

                val content2image = snapshot.child("content2image").value.toString()
                val content2imageUrl = FirebaseStorage.getInstance().getReferenceFromUrl(content2image)
                content2imageUrl.downloadUrl.addOnCompleteListener { task ->
                    val downloadUrl = task.result
                    Glide.with(detailContent2ImageView).load(downloadUrl)
                        .into(detailContent2ImageView)
                }

                val content2text = snapshot.child("content2text").value.toString()
                detailContent2TextView.text = content2text

                val subContent1image = snapshot.child("subcontent1image").value.toString()
                val subContent1imageUrl = FirebaseStorage.getInstance().getReferenceFromUrl(subContent1image)
                subContent1imageUrl.downloadUrl.addOnCompleteListener { task ->
                    val downloadUrl = task.result
                    Glide.with(subContent1ImageView).load(downloadUrl)
                        .into(subContent1ImageView)
                }

                val subContent2image = snapshot.child("subcontent2image").value.toString()
                val subContent2imageUrl = FirebaseStorage.getInstance().getReferenceFromUrl(subContent2image)
                subContent2imageUrl.downloadUrl.addOnCompleteListener { task ->
                    val downloadUrl = task.result
                    Glide.with(subContent2ImageView).load(downloadUrl)
                        .into(subContent2ImageView)
                }

                val addresText = snapshot.child("addresText").value.toString()
                detailAddresTextView.text = addresText


                val TelText = snapshot.child("TelText").value.toString()
                detailTelTextView.text = TelText

                val RegularHolidayText = snapshot.child("RegularHolidaytext").value.toString()
                detailRegularHolidayTextView.text = RegularHolidayText


            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
