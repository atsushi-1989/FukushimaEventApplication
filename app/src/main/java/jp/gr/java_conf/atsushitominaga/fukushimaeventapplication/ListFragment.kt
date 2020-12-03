package jp.gr.java_conf.atsushitominaga.fukushimaeventapplication

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_list.*
import java.lang.RuntimeException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"


/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {

    private var mListener : OnListFragmentInteractionListener? = null

    var firebaseReference : DatabaseReference? = null
    var layoutManager: LinearLayoutManager? = null

    lateinit var firebaseAdapter: FirebaseRecyclerAdapter<Model, Holder>



    // TODO: Rename and change types of parameters
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)



    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        firebaseReference = FirebaseDatabase.getInstance().reference!!

        layoutManager = GridLayoutManager(ListActivity(),2)
        listRecyclerView.layoutManager = layoutManager


        var category = param1 as String

        val query = firebaseReference!!.child(category)
        val options = FirebaseRecyclerOptions.Builder<Model>().setQuery(query,Model::class.java).build()


        firebaseAdapter = object: FirebaseRecyclerAdapter<Model,Holder>(options){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
                val view  = LayoutInflater.from(parent!!.context).inflate(R.layout.list_content, parent, false)
                return Holder(view)
            }

            override fun onBindViewHolder(holder: Holder, position: Int, model: Model) {

                when(model.tagCollor){
                    1 -> holder.contentTag.setBackgroundColor(Color.BLUE)
                    2 -> holder.contentTag.setBackgroundColor(Color.RED)
                    3 -> holder.contentTag.setBackgroundColor(Color.YELLOW)
                    4 -> holder.contentTag.setBackgroundColor(Color.GREEN)
                }


                holder.contentDate.text = model.dateText
                holder.contentTitle.text = model.titleText
                holder.contentPlace.text = model.placeText

                val imageUrl = model.imageUrl
                val storageRef = FirebaseStorage.getInstance().getReferenceFromUrl(imageUrl)
                storageRef.downloadUrl.addOnCompleteListener { task ->
                    val downloadUrl = task.result
                    Glide.with(holder.contentImageView.context).load(downloadUrl).into(holder.contentImageView)
                }

                holder.contentCard.setOnClickListener{
                    mListener?.onListItemClicked(model.url)


                }
            }
        }
        listRecyclerView.adapter = firebaseAdapter


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener){
            mListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnListFragmentInteractionListener{
        fun onListItemClicked(url : String)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)

                }
            }
    }

    override fun onResume() {
        super.onResume()
        firebaseAdapter.startListening()
    }

    override fun onPause() {
        super.onPause()
        firebaseAdapter.stopListening()
    }
}
