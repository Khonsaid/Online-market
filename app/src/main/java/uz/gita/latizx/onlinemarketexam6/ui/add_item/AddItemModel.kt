package uz.gita.latizx.onlinemarketexam6.ui.add_item

import uz.gita.latizx.onlinemarketexam6.source.room.AppDataBase
import uz.gita.latizx.onlinemarketexam6.source.room.entity.ItemEntity

class AddItemModel : AddItemContract.Model {
    private val db = AppDataBase.instance.itemDao()

    override fun saveItem(itemEntity: ItemEntity): Long = db.addItem(itemEntity)
}