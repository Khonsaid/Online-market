package uz.gita.latizx.onlinemarketexam6.ui.items

import uz.gita.latizx.onlinemarketexam6.source.room.entity.ItemEntity

interface ItemsByCategoryContract {
    interface Model {
        fun getItemsByCategory(categoryId: Long): List<ItemEntity>
        fun updateItem(itemEntity: ItemEntity): Int
    }

    interface View {
        fun lostData(list: List<ItemEntity>)
        fun openAddScreen(categoryId: Long)
        fun openPrevScreen()

    }

    interface Presenter {
        fun getCategoryId(categoryId: Long)
        fun clickBack()
        fun clickLike(itemEntity: ItemEntity)
        fun clickAdd()
    }
}