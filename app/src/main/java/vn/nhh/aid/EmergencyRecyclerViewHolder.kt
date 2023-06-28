package vn.nhh.aid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmergencyRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val ans: TextView = itemView.findViewById(R.id.mcAnswer)

    fun getAns(): TextView {
        return ans
    }
}