package uz.gita.latizx.onlinemarketexam6.source.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import uz.gita.latizx.onlinemarketexam6.source.room.entity.ItemEntity

@Dao
interface ItemDao {

    @Query("SELECT * FROM ItemEntity")
    fun getAllItem(): List<ItemEntity>

    @Query("SELECT * FROM ItemEntity WHERE categoryId = :categoryId")
    fun getItemsByCategory(categoryId: Long): List<ItemEntity>

    @Query("SELECT * FROM ItemEntity WHERE id = :itemId LIMIT 1")
    fun getItemById(itemId: Long): List<ItemEntity>

    @Query("SELECT * FROM ItemEntity WHERE favourite = 1")
    fun getAllFavourite(): List<ItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItem(itemEntity: ItemEntity): Long

    @Update
    fun updateItem(itemEntity: ItemEntity): Int
}