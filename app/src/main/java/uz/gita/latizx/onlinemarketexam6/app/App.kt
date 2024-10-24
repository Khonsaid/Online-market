package uz.gita.latizx.onlinemarketexam6.app

import android.app.Application
import android.content.Context
import uz.gita.latizx.onlinemarketexam6.source.pref.MyPref

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MyPref.init(this.getSharedPreferences("online_shop_pref", Context.MODE_PRIVATE))
    }
}