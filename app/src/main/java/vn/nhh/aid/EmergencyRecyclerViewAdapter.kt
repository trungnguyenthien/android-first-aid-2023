package vn.nhh.aid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmergencyRecyclerViewAdapter() : RecyclerView.Adapter<EmergencyRecyclerViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return R.layout.mc_recycler_view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmergencyRecyclerViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return EmergencyRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmergencyRecyclerViewHolder, position: Int) {
        val ansList = holder.itemView.context.resources.getStringArray(R.array.lesson_name_txt)

        holder.getAns().text = ansList[position]
    }

    override fun getItemCount(): Int {
        return 2 // TODO: I don't know how to set dynamic number T.T
    }
}

/*
class LessonRecyclerViewAdapter(
    private val context: Context,
    private val lessonList: ArrayList<LessonModel>
) : RecyclerView.Adapter<MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView
        private val textView: TextView

        init {
            imageView = itemView.findViewById(R.id.lessonImage)
            textView = itemView.findViewById(R.id.lessonText)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.lesson_recycler_view, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val lessonText = holder.itemView.findViewById<TextView>(R.id.lessonText)
        val lessonImage = holder.itemView.findViewById<ImageView>(R.id.lessonImage)

        lessonText.text = lessonList[position].name
        lessonList[position].image?.let { lessonImage.setImageResource(it) }
    }

    override fun getItemCount(): Int {
        return lessonList.size
    }

}
 */