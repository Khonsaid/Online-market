package uz.gita.latizx.onlinemarketexam6.ui.items

import uz.gita.latizx.onlinemarketexam6.source.room.entity.ItemEntity
import uz.gita.latizx.onlinemarketexam6.source.room.entity.KarzinaEntity

interface ItemsByCategoryContract {
    interface Model {
        fun getItemsByCategory(categoryId: Long): List<ItemEntity>
        fun updateItem(itemEntity: ItemEntity): Int
        fun addItemInKarzina(karzinaEntity: KarzinaEntity)
    }

    interface View {
        fun lostData(list: List<ItemEntity>)
        fun openAddScreen(categoryId: Long)
        fun openPrevScreen()
        fun openInfoScreen(itemEntity: ItemEntity)
        fun showToast(itemEntity: ItemEntity)
    }

    interface Presenter {
        fun getCategoryId(categoryId: Long)
        fun clickBack()
        fun clickAdd()
        fun clickLike(itemEntity: ItemEntity)
        fun clickBuy(itemEntity: ItemEntity)
        fun clickItem(itemEntity: ItemEntity)
    }
}