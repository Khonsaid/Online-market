package uz.gita.latizx.onlinemarketexam6.ui.items

import uz.gita.latizx.onlinemarketexam6.source.room.AppDataBase
import uz.gita.latizx.onlinemarketexam6.source.room.entity.ItemEntity

class ItemsByCategoryModel : ItemsByCategoryContract.Model {
    private val db = AppDataBase.instance.itemDao()
    override fun getItemsByCategory(categoryId: Long): List<ItemEntity> = db.getItemsByCategory(categoryId)

    override fun updateItem(itemEntity: ItemEntity) = db.updateItem(itemEntity)
}