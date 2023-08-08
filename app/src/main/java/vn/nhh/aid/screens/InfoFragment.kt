package vn.nhh.aid.screens

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText
import vn.nhh.aid.R
import vn.nhh.aid.pushPageStack

class InfoFragment: BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.info_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = this.activity?.getSharedPreferences("UserInfo", MODE_PRIVATE)
        val editor = sharedPref?.edit()
        val Save: AppCompatButton = view.findViewById(R.id.Save)
        val Username: TextInputEditText = view.findViewById(R.id.Username)
        val Phone: TextInputEditText = view.findViewById(R.id.Phone)
        view.apply{
            Save.setOnClickListener{
                val ten = Username.text.toString()
                val dienthoai = Phone.text.toString()

                editor?.apply{
                    putString("ten_nguoi_dung", ten)
                    putString("dien_thoai", dienthoai)
                }
                pushPageStack(HomeFragment())
            }
        }
    }
}