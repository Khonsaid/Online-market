package uz.gita.latizx.onlinemarketexam6.utils

import uz.gita.latizx.onlinemarketexam6.source.room.entity.ItemEntity
import uz.gita.latizx.onlinemarketexam6.source.room.entity.KarzinaEntity

fun ItemEntity.toKarzinaEntity(count: Int): KarzinaEntity {
    return KarzinaEntity(
        id = 0,
        name = this.name,
        price = this.price,
        description = this.description,
        discount = this.discount,
        count = count,
        categoryId = this.categoryId,
        image = this.image
    )
}

fun String.formatWithSeparator(): String {
    return this.reversed().chunked(3).joinToString(" ").reversed()
}
