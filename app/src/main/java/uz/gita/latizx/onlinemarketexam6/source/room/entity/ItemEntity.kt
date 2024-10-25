package uz.gita.latizx.onlinemarketexam6.source.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("categoryId")
        )
    ]
)
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val price: Double,
    val image: String,
    val description: String,
    val discount: Int = 0,
    val favourite: Int = 0,
    val categoryId: Long
)
