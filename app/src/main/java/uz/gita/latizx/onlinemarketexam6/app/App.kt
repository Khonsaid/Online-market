package uz.gita.latizx.onlinemarketexam6.app

import android.app.Application
import android.content.Context
import androidx.room.Room
import uz.gita.latizx.onlinemarketexam6.source.pref.MyPref
import uz.gita.latizx.onlinemarketexam6.source.room.AppDataBase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MyPref.init(this.getSharedPreferences("online_shop_pref", Context.MODE_PRIVATE))
        AppDataBase.init(Room.databaseBuilder(this, AppDataBase::class.java, "OnlineMarket.db").allowMainThreadQueries().build())
    }
}