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
data class KarzinaEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val price: Double,
    val description: String,
    val discount: Int = 0,
    val count: Int = 1,
    val totalPrice: Double,
    val categoryId: Long,
    val image: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as KarzinaEntity

        return image.contentEquals(other.image)
    }

    override fun hashCode(): Int {
        return image.contentHashCode()
    }
}
