package vn.nhh.aid.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vn.nhh.aid.R
import vn.nhh.aid.screens.DangerLevelFragment

class RecyclerViewAdapter(
    private val options: List<DangerLevelFragment.Option>?,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val bigText: TextView = itemView.findViewById(R.id.Name)
        val smallText: TextView = itemView.findViewById(R.id.Desc)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.general_recycler_view, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount() = options?.size ?: 0

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val option = options?.elementAtOrNull(position) ?: return
        if (option.symptoms == "null") {
            holder.smallText.visibility = View.GONE
        }
        holder.bigText.text = option.option
        holder.smallText.text = option.symptoms
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}