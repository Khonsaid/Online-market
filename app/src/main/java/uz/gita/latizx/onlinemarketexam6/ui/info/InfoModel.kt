package uz.gita.latizx.onlinemarketexam6.ui.info

import uz.gita.latizx.onlinemarketexam6.source.room.AppDataBase
import uz.gita.latizx.onlinemarketexam6.source.room.entity.ItemEntity
import uz.gita.latizx.onlinemarketexam6.source.room.entity.KarzinaEntity

class InfoModel : InfoContract.Model {
    private val db = AppDataBase.instance

    override fun addToCard(karzinaEntity: KarzinaEntity) {
        db.karzinaDao().addItem(karzinaEntity)
    }

    override fun updateItem(itemEntity: ItemEntity) = db.itemDao().updateItem(itemEntity)
    override fun getItemById(itemId: Long): List<ItemEntity> = db.itemDao().getItemById(itemId)

}