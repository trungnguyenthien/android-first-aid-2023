package vn.nhh.aid.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject
import vn.nhh.aid.R
import vn.nhh.aid.pushPageStack
import vn.nhh.aid.shareMainActivity
import vn.nhh.aid.utils.readJsonArrayFromAssets
import vn.nhh.aid.utils.toList
import vn.nhh.aid.views.RecyclerViewAdapter

private const val D: Int = 0

class DangerlevelFragment: BaseFragment(), RecyclerViewAdapter.OnItemClickListener {
    private var position: Int? = null
    private lateinit var recyclerview: RecyclerView
    private var Prolist: List<Procedure> = emptyList()
    private lateinit var adapter: RecyclerViewAdapter
    private var level: Level? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(D.toString(), -1)
            level = getLevelRoot()
            Prolist = level?.Options!!
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dangerlevel_low_high, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerview = view.findViewById(R.id.recyclerView)
        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        adapter = RecyclerViewAdapter(Prolist, this)
        recyclerview.adapter = adapter
    }

    override fun onItemClick(position: Int){
        val ItemClick: String = Prolist[position].guideline
        pushPageStack(GuideFragment.newInstance(ItemClick))
    }

    data class Level(
        val level: String,
        val Options: List<Procedure> = emptyList()
    )
    data class Procedure(
        val Name: String,
        val Symptoms: String?,
        val guideline: String
    )

    fun getLevelRoot(): Level? {
        val root = readJsonArrayFromAssets(shareMainActivity(), "level.json") ?: return null
        return parseLevel(root)
    }

    fun parseProList(json: JSONObject): Procedure {
        return Procedure (
            Name = json.optString("Option"),
            Symptoms = json.optString("Symptoms"),
            guideline = json.optString("guideline")
        )
    }
    private fun parseLevel(json: JSONArray): Level? {
        return Level(
            level = json.optJSONObject(position!!).getString("Level"),
            Options = json.optJSONObject(position!!).toList("Options").map {parseProList(it)}
        )
    }

    companion object {
        @JvmStatic
        fun newInstance(position: Int) = DangerlevelFragment().apply {
            arguments = Bundle().apply {
                putInt(D.toString(), position)
            }
        }
    }
}


