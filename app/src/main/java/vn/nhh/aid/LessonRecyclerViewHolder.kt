package vn.nhh.aid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
// import vn.nhh.aid.LessonRecyclerViewAdapter.MyViewHolder

class LessonRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val name: TextView = itemView.findViewById(R.id.lessonNameText)
    private val desc: TextView = itemView.findViewById(R.id.lessonDescText)

    fun getName(): TextView {
        return name
    }

    fun getDesc(): TextView {
        return desc
    }
}