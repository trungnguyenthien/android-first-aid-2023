package vn.nhh.aid.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import vn.nhh.aid.R
import vn.nhh.aid.pushPageStack
import vn.nhh.aid.shareMainActivity
import vn.nhh.aid.utils.readJsonArrayFromAssets
import vn.nhh.aid.utils.toList
import vn.nhh.aid.views.RecyclerViewAdapter

private const val D = "0"
private const val N = "-1"

class DangerlevelFragment: BaseFragment(), RecyclerViewAdapter.OnItemClickListener {
    private var level: Int = 0
    private var position: Int = 0
    private lateinit var recyclerview: RecyclerView
    private var prolist: List<Procedure>? = emptyList()
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            level = it.getInt(D, -1)
            position = it.getInt(N, -1)
            prolist = getListRoot()
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dangerlevel_low_high, container, false)
    }
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerview = view.findViewById(R.id.recyclerView)
        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        val title: TextView = view.findViewById(R.id.textView)
        if (position != -1){
            prolist = prolist?.get(position)?.options
            title.text = prolist?.get(position)?.question
        } else if (level == 0){
            title.text = "Các bênh nguy hiểm đến tính mạng"
        } else { title.text = "Các bệnh có thể trì hoãn"}
        adapter = RecyclerViewAdapter(prolist, this)
        recyclerview.adapter = adapter
    }


    override fun onItemClick(position: Int){
        val itemClick: String = prolist?.get(position)!!.guideline
        if (itemClick.isNotEmpty()){
            pushPageStack(GuideFragment.newInstance(itemClick))
        } else {
            pushPageStack(newInstance(level, position))
        }
    }

    data class Procedure(
        val Name: String,
        val Symptoms: String,
        val guideline: String,
        val question: String?,
        val options: List<Procedure> = emptyList()
    )

    private fun getListRoot(): List<Procedure>? {
        val root = readJsonArrayFromAssets(shareMainActivity(), "level.json") ?: return null
        return root.optJSONObject(level).toList("Options").map {parseProList(it)}
    }

    private fun parseProList(json: JSONObject): Procedure {
        return Procedure (
            Name = json.optString("Option"),
            Symptoms = json.optString("symptoms"),
            guideline = json.optString("guideline"),
            question = json.optString("question"),
            options = json.toList("options").map { parseProList(it)}
        )
    }

    companion object {
        @JvmStatic
        fun newInstance(position: Int, Clicked: Int) = DangerlevelFragment().apply {
            arguments = Bundle().apply {
                putInt(D, position)
                putInt(N, Clicked)
            }
        }
    }
}


