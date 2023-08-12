package vn.nhh.aid.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vn.nhh.aid.R
import vn.nhh.aid.screens.DangerlevelFragment

class RecyclerViewAdapter(
    private val ProList: List<DangerlevelFragment.Procedure>?,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val proText : TextView = itemView.findViewById(R.id.Name)
        val sypText : TextView = itemView.findViewById(R.id.Desc)

        init{
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?){
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from (parent.context).inflate(R.layout.general_recycler_view,parent,false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ProList!!.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val procedure = ProList!![position]
        if (procedure.Symptoms == "null"){
            holder.sypText.visibility = View.GONE
        }
        holder.proText.text = procedure.Name
        holder.sypText.text = procedure.Symptoms
    }
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}