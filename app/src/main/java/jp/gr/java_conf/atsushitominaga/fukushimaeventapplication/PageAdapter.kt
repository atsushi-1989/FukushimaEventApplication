package jp.gr.java_conf.atsushitominaga.fukushimaeventapplication

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PageAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> NewsFirstFragment()
            1 -> NewsSecondFragment()
            2 -> NewsTherdFragment()
            else -> NewsFirstFragment()
        }
    }
}