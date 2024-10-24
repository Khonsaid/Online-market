package uz.gita.latizx.onlinemarketexam6.source.pref

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.gita.latizx.onlinemarketexam6.models.UserModel

class MyPref private constructor() {
    private val gson = Gson()

    companion object {
        private lateinit var instance: MyPref
        private lateinit var pref: SharedPreferences

        fun init(sharedPreferences: SharedPreferences) {
            if (!(::instance.isInitialized)) instance = MyPref()
            pref = sharedPreferences
        }

        fun getInstance(): MyPref = instance
    }

    fun getUser(): UserModel {
        val type = object : TypeToken<UserModel>() {}.type
        return gson.fromJson(pref.getString("user", null), type)
    }

    fun setUser(userModel: UserModel) {
        pref.edit().putString("user", gson.toJson(userModel)).apply()
    }
}