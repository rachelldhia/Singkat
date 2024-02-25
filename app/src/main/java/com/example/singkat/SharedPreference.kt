package com.example.singkat

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity

class SharedPreference(activity: FragmentActivity) : SharedPreferences {
    val login = "login"
    val myPref = "Main_Pref"
    val sharedPreference : SharedPreferences

    init {
        sharedPreference = activity.getSharedPreferences(myPref, Context.MODE_PRIVATE)
    }

    fun getStatusLogin(status : Boolean){
        sharedPreference.edit().putBoolean(login, status). apply()
    }

    fun getStatusLogin() : Boolean{
        return sharedPreference.getBoolean(login, false)
    }

    //methot gga dipake

    override fun getAll(): MutableMap<String, *> {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun getString(p0: String?, p1: String?): String? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun getStringSet(p0: String?, p1: MutableSet<String>?): MutableSet<String>? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun getInt(p0: String?, p1: Int): Int {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun getLong(p0: String?, p1: Long): Long {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun getFloat(p0: String?, p1: Float): Float {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun getBoolean(p0: String?, p1: Boolean): Boolean {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun contains(p0: String?): Boolean {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun edit(): SharedPreferences.Editor {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun registerOnSharedPreferenceChangeListener(p0: SharedPreferences.OnSharedPreferenceChangeListener?) {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun unregisterOnSharedPreferenceChangeListener(p0: SharedPreferences.OnSharedPreferenceChangeListener?) {
        throw UnsupportedOperationException("Not yet implemented")
    }


}