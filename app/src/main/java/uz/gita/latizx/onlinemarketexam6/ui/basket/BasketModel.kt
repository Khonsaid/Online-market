package uz.gita.latizx.onlinemarketexam6.ui.basket

import uz.gita.latizx.onlinemarketexam6.source.room.AppDataBase
import uz.gita.latizx.onlinemarketexam6.source.room.entity.KarzinaEntity

class BasketModel : BasketContract.Model {
    private val db = AppDataBase.instance.karzinaDao()

    override fun getAllItem(): List<KarzinaEntity> = db.getAllItem()
    override fun removeKorzina(karzinaEntity: KarzinaEntity) {
        db.deleteItem(karzinaEntity)
    }

    override fun updateItemKorzina(karzinaEntity: KarzinaEntity) {
        db.updateItem(karzinaEntity)
    }

    override fun getTotalSum(): Int = db.getSumKarina()
    override fun getCountKorzina(): Int = db.getCountKarina()

    override fun getTotalSumWithDiscount(): Int = db.getTotalSum()
    override fun removeAll() {
        db.removeAll()
    }
}