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
        totalPrice = this.price * count,
        categoryId = this.categoryId,
        image = this.image
    )
}

