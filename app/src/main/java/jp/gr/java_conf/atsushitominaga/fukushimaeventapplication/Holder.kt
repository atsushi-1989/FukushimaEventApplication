package jp.gr.java_conf.atsushitominaga.fukushimaeventapplication

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class Holder(view : View) : RecyclerView.ViewHolder(view) {

    val contentCard : CardView
    val contentTag : View
    val contentImageView : ImageView
    val contentDate : TextView
    val contentTitle : TextView
    val contentPlace : TextView

    init {
        contentCard = view.findViewById(R.id.contentCard)
        contentTag = view.findViewById(R.id.contentTag)
        contentImageView = view.findViewById(R.id.contentImageView)
        contentDate = view.findViewById(R.id.contentDate)
        contentTitle = view.findViewById(R.id.contentTitle)
        contentPlace = view.findViewById(R.id.contentPlace)
    }

}