package vn.nhh.aid.screens

import androidx.fragment.app.FragmentManager

interface PreventBack {
    fun handleBackPressed(manager: FragmentManager)
    fun allowBack(): Boolean
}