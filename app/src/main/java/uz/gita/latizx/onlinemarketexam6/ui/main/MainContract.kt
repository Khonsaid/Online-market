package uz.gita.latizx.onlinemarketexam6.ui.main

import com.denzcoskun.imageslider.models.SlideModel
import uz.gita.latizx.onlinemarketexam6.source.room.entity.ItemEntity
import uz.gita.latizx.onlinemarketexam6.source.room.entity.KarzinaEntity

interface MainContract {
    interface Model {
        fun getAdvertisingList(): ArrayList<SlideModel>
        fun getAllItem(): List<ItemEntity>
        fun updateItem(itemEntity: ItemEntity): Int
        fun addItemInKarzina(karzinaEntity: KarzinaEntity)

    }

    interface View {
        fun loadAdvertising(list: ArrayList<SlideModel>)
        fun loadItems(list: List<ItemEntity>)
        fun showToast(itemEntity: ItemEntity)
        fun openNextScreen()
        fun showPlaceHolder(isVisible: Boolean)
        fun showImageSlider(isVisible: Boolean)
        fun openInfoScreen(itemId:Long)
    }

    interface Presenter {
        fun clickLike(itemEntity: ItemEntity)
        fun clickBuy(itemEntity: ItemEntity)
        fun filterItems(query: String?)
        fun clickFavouriteBtn()
        fun clickItem(itemId: Long)
    }
}