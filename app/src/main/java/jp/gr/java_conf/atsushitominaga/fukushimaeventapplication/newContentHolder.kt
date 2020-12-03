package jp.gr.java_conf.atsushitominaga.fukushimaeventapplication

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class newContentHolder(view: View) : RecyclerView.ViewHolder(view) {
    val newContentCard : CardView
    val newContentTag : View
    val newContentImage : ImageView
    val newContentDay : TextView
    val newContentPlace : TextView
    val newContentTitle : TextView

    init {
        newContentCard = view.findViewById(R.id.newContentCardView)
        newContentTag = view.findViewById(R.id.newContentTag)
        newContentImage = view.findViewById(R.id.newContentImage)
        newContentDay = view.findViewById(R.id.newContentDay)
        newContentPlace = view.findViewById(R.id.newContentPlace)
        newContentTitle = view.findViewById(R.id.newContentTitle)
    }
}