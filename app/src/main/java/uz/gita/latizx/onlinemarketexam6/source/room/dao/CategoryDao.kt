package uz.gita.latizx.onlinemarketexam6.source.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.gita.latizx.onlinemarketexam6.source.room.entity.CategoryEntity

@Dao
interface CategoryDao {

    @Query("SELECT * FROM CategoryEntity")
    fun getAllCategory(): List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCategory(categoryEntity: CategoryEntity): Long
}