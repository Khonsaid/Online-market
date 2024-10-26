package uz.gita.latizx.onlinemarketexam6.source.room.dao

import android.os.FileObserver.DELETE
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

    @Query("SELECT SUM(price) FROM KARZINAENTITY")
    fun getSumKarina(): Int

    @Query("SELECT SUM(count) FROM KARZINAENTITY")
    fun getCountKarina(): Int

    @Query("SELECT SUM(price - ((price * discount) / 100)) FROM KarzinaEntity")
    fun getTotalSum(): Int

    @Delete
    fun deleteItem(karzinaEntity: KarzinaEntity): Int

    @Query("DELETE FROM KarzinaEntity")
    fun removeAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItem(karzinaEntity: KarzinaEntity): Long

    @Update
    fun updateItem(karzinaEntity: KarzinaEntity): Int
}