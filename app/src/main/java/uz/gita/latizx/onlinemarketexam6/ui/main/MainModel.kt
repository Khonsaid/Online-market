package uz.gita.latizx.onlinemarketexam6.ui.main

import com.denzcoskun.imageslider.models.SlideModel
import uz.gita.latizx.onlinemarketexam6.R
import uz.gita.latizx.onlinemarketexam6.source.room.AppDataBase
import uz.gita.latizx.onlinemarketexam6.source.room.entity.ItemEntity
import uz.gita.latizx.onlinemarketexam6.source.room.entity.KarzinaEntity

class MainModel : MainContract.Model {
    private val db = AppDataBase.instance
    override fun getAdvertisingList(): ArrayList<SlideModel> = loadAdvertising()

    override fun getAllItem(): List<ItemEntity> = db.itemDao().getAllItem()

    override fun updateItem(itemEntity: ItemEntity) = db.itemDao().updateItem(itemEntity)
    override fun addItemInKarzina(karzinaEntity: KarzinaEntity) {
        db.karzinaDao().addItem(karzinaEntity)
    }

    private fun loadAdvertising(): ArrayList<SlideModel> =
        arrayListOf(
            SlideModel(R.drawable.img_advertising5, ""),
            SlideModel(R.drawable.img_advertising3, ""),
            SlideModel(R.drawable.img_advertising2, ""),
            SlideModel(R.drawable.img_advertising1, ""),
            SlideModel(R.drawable.img_advertising4, ""),
        )
}