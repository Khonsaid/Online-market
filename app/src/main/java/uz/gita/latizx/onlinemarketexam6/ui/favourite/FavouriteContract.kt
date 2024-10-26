package uz.gita.latizx.onlinemarketexam6.ui.favourite

import uz.gita.latizx.onlinemarketexam6.source.room.entity.ItemEntity
import uz.gita.latizx.onlinemarketexam6.source.room.entity.KarzinaEntity

interface FavouriteContract {
    interface Model {
        fun getAllFavourite(): List<ItemEntity>
        fun updateItem(itemEntity: ItemEntity): Int
        fun addItemInKarzina(karzinaEntity: KarzinaEntity)
    }

    interface View {
        fun lostData(list: List<ItemEntity>)
        fun openPrevScreen()
        fun showToast(itemEntity: ItemEntity)
        fun openInfoScreen(itemId:Long)
    }

    interface Presenter {
        fun clickLike(itemEntity: ItemEntity)
        fun clickBack()
        fun clickBuy(itemEntity: ItemEntity)
        fun clickItem(itemId: Long)
    }
}