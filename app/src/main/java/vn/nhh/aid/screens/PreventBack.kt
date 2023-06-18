package vn.nhh.aid.screens

import androidx.fragment.app.FragmentManager

interface PreventBack {
    fun handleBackPressed(fmanager: FragmentManager)
    fun allowBack(): Boolean
}