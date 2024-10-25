package uz.gita.latizx.onlinemarketexam6.source.room

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.latizx.onlinemarketexam6.source.room.dao.CategoryDao
import uz.gita.latizx.onlinemarketexam6.source.room.dao.ItemDao
import uz.gita.latizx.onlinemarketexam6.source.room.entity.CategoryEntity
import uz.gita.latizx.onlinemarketexam6.source.room.entity.ItemEntity

@Database(entities = [CategoryEntity::class, ItemEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun itemDao(): ItemDao

    companion object {
        lateinit var instance: AppDataBase
            private set

        fun init(room: AppDataBase) {
            if (!(::instance.isInitialized)) instance = room
        }
    }
}