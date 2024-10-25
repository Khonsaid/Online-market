package uz.gita.latizx.onlinemarketexam6.source.room

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.latizx.onlinemarketexam6.source.room.dao.CategoryDao
import uz.gita.latizx.onlinemarketexam6.source.room.entity.CategoryEntity

@Database(entities = [CategoryEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao

    companion object {
        lateinit var instance: AppDataBase
            private set

        fun init(room: AppDataBase) {
            if (!(::instance.isInitialized)) instance = room
        }
    }
}