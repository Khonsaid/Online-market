package uz.gita.latizx.onlinemarketexam6.ui.catalog

import uz.gita.latizx.onlinemarketexam6.source.room.entity.CategoryEntity

interface CategoryContract {
    interface Model {
        fun getAllCategory(): ArrayList<CategoryEntity>
        fun getImgCategory(): List<Int>
        fun saveCategory(categoryEntity: CategoryEntity): Long
    }

    interface View {
        fun loadData(list: ArrayList<CategoryEntity>)
        fun openItemByCategory(categoryEntity: CategoryEntity)
        fun showDialogAdd(list: List<Int>)
        fun notifyItemChanged(position: Int, isVisible: Boolean)
        fun showSuccessDialog()
    }

    interface Presenter {
        fun clickAddBtn()
        fun clickItem(categoryEntity: CategoryEntity)
        fun addCategory(categoryEntity: CategoryEntity)
    }
}