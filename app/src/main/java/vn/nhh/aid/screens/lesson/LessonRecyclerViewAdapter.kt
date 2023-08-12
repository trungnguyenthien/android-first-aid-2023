package vn.nhh.aid.screens.lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vn.nhh.aid.R

// import vn.nhh.aid.screens.lesson.LessonRecyclerViewAdapter.MyViewHolder

class LessonRecyclerViewAdapter() : RecyclerView.Adapter<LessonRecyclerViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return R.layout.general_recycler_view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonRecyclerViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return LessonRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: LessonRecyclerViewHolder, position: Int) {
        val lessonName = holder.itemView.context.resources.getStringArray(R.array.lesson_name_txt)
        val lessonDesc = holder.itemView.context.resources.getStringArray(R.array.lesson_description_txt)

        holder.getName().text = lessonName[position]
        holder.getDesc().text = lessonDesc[position]
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