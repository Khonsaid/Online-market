package uz.gita.latizx.onlinemarketexam6.ui.favourite

import uz.gita.latizx.onlinemarketexam6.source.room.AppDataBase
import uz.gita.latizx.onlinemarketexam6.source.room.entity.ItemEntity
import uz.gita.latizx.onlinemarketexam6.source.room.entity.KarzinaEntity

class FavouriteModel : FavouriteContract.Model {

    private val db = AppDataBase.instance
    override fun getAllFavourite(): List<ItemEntity> = db.itemDao().getAllFavourite()

    override fun updateItem(itemEntity: ItemEntity) = db.itemDao().updateItem(itemEntity)
    override fun addItemInKarzina(karzinaEntity: KarzinaEntity) {
        db.karzinaDao().addItem(karzinaEntity)
    }
}