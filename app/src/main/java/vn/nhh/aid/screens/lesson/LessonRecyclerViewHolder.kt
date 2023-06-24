package vn.nhh.aid.screens.lesson

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vn.nhh.aid.R

// import vn.nhh.aid.screens.lesson.LessonRecyclerViewAdapter.MyViewHolder

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