package uz.gita.latizx.onlinemarketexam6.ui.add_item

import android.graphics.Bitmap
import uz.gita.latizx.onlinemarketexam6.source.room.entity.ItemEntity

interface AddItemContract {
    interface Model {
        fun saveItem(itemEntity: ItemEntity): Long
    }

    interface View {
        fun showSuccessDialogAndOpenPrevScreen()
        fun showDiscount(isVisibility: Boolean)
        fun openPrevScreen()
        fun openGallery()
        fun enableBtn(isEnable: Boolean)

        fun errorName(error: String)
        fun errorDescription(error: String)
        fun errorPrice(error: String)
        fun errorDisCountName(error: String)
    }

    interface Presenter {
        fun clickSelectImg()
        fun clickSaveBtn(name: String, description: String, price: String, discount: String, categoryId: Long)
        fun clickBack()
        fun setBitmap(bitmap: Bitmap)

        fun checkBoxListener(isSelected: Boolean)
        fun setNameListener(name: String)
        fun setDescriptionListener(description: String)
        fun setPriceListener(price: String)
        fun setDisCountNameListener(discount: String)
    }
}