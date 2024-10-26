package uz.gita.latizx.onlinemarketexam6.ui.info

import uz.gita.latizx.onlinemarketexam6.source.room.entity.ItemEntity
import uz.gita.latizx.onlinemarketexam6.source.room.entity.KarzinaEntity

interface InfoContract {
    interface Model {
        fun addToCard(karzinaEntity: KarzinaEntity)
        fun updateItem(itemEntity: ItemEntity): Int
        fun getItemById(itemId: Long): List<ItemEntity>
    }

    interface View {
        fun openPrevScreen()
        fun openCartScreen()
        fun loadData(itemEntity: ItemEntity)
        fun showToast(itemEntity: ItemEntity)
        fun changeLike(like: Int)
    }

    interface Presenter {
        fun clickBack()
        fun clickAddCart()
        fun clickGoToCart()
        fun updateItem()
    }
}