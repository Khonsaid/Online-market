package uz.gita.latizx.onlinemarketexam6.ui.items

import uz.gita.latizx.onlinemarketexam6.source.room.AppDataBase
import uz.gita.latizx.onlinemarketexam6.source.room.entity.ItemEntity
import uz.gita.latizx.onlinemarketexam6.source.room.entity.KarzinaEntity

class ItemsByCategoryModel : ItemsByCategoryContract.Model {
    private val db = AppDataBase.instance
    override fun getItemsByCategory(categoryId: Long): List<ItemEntity> = db.itemDao().getItemsByCategory(categoryId)

    override fun updateItem(itemEntity: ItemEntity) = db.itemDao().updateItem(itemEntity)
    override fun addItemInKarzina(karzinaEntity: KarzinaEntity) {
        db.karzinaDao().addItem(karzinaEntity)
    }
}