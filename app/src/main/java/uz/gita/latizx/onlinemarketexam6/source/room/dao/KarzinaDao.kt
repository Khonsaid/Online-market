package uz.gita.latizx.onlinemarketexam6.source.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import uz.gita.latizx.onlinemarketexam6.source.room.entity.KarzinaEntity

@Dao
interface KarzinaDao {

    @Query("SELECT * FROM KarzinaEntity")
    fun getAllItem(): List<KarzinaEntity>

    @Query("SELECT COUNT(*) FROM KARZINAENTITY")
    fun getSizeKarina(): Int

    @Delete
    fun deleteItem(karzinaEntity: KarzinaEntity): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItem(karzinaEntity: KarzinaEntity): Long

    @Update
    fun updateItem(karzinaEntity: KarzinaEntity): Int


}