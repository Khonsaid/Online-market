package uz.gita.latizx.onlinemarketexam6.ui.add_item

import android.graphics.Bitmap
import android.util.Log
import uz.gita.latizx.onlinemarketexam6.source.room.AppDataBase
import uz.gita.latizx.onlinemarketexam6.source.room.entity.ItemEntity
import uz.gita.latizx.onlinemarketexam6.utils.BitmapConverter

class AddItemPresenter(private val view: AddItemContract.View) : AddItemContract.Presenter {
    private val model:AddItemContract.Model = AddItemModel()
    private var isNameValid = false
    private var isDescriptionValid = false
    private var isPriceValid = false
    private var isDisCountValid = false
    private var bitmap: Bitmap? = null

    override fun clickSaveBtn(name: String, description: String, price: String, discount: String, categoryId: Long) {
        Log.d("TTT", "clickSaveBtn: $discount")
        val disCount = if (discount.isEmpty() || discount.isBlank()) 0 else discount.toInt()

        if (model.saveItem(
                ItemEntity(
                    id = 0,
                    name = name,
                    price = price.toDouble(),
                    image = BitmapConverter.bitmapToByteArray(bitmap!!),
                    description = description,
                    discount = disCount,
                    categoryId = categoryId,
                )
            ) > 0
        ) view.showSuccessDialogAndOpenPrevScreen()
    }

    override fun checkBoxListener(isSelected: Boolean) {
        view.showDiscount(isSelected)
    }

    override fun setNameListener(name: String) {
        isNameValid = when {
            name.isEmpty() -> {
                view.errorName("Name cannot be empty!")
                false
            }

            name.length < 3 -> {
                view.errorName("Name must be longer than 3 characters!")
                false
            }

            else -> {
                view.errorName("")
                true
            }
        }
        checkFormValidity()
    }

    override fun setDescriptionListener(description: String) {
        isDescriptionValid = when {
            description.isEmpty() -> {
                view.errorDescription("Description cannot be empty!")
                false
            }

            description.length < 3 -> {
                view.errorDescription("Description must be longer than 3 characters!")
                false
            }

            else -> {
                view.errorDescription("")
                true
            }
        }
        checkFormValidity()
    }

    override fun setPriceListener(price: String) {
        if (price.isBlank() || price.isEmpty()) return
        isPriceValid = when {
            price.toDouble() < 500 -> {
                view.errorPrice("Price must be at least 500")
                false
            }

            else -> {
                view.errorPrice("")
                true
            }
        }
        checkFormValidity()
    }

    override fun setDisCountNameListener(discount: String) {
        if (discount.isBlank() || discount.isEmpty()) return
        isDisCountValid = when {
            discount.toInt() < 1 -> {
                view.errorDisCountName("DisCount must be at least 1%")
                false
            }

            discount.toInt() > 100 -> {
                view.errorDisCountName("DisCount must be less than 100")
                false
            }

            else -> {
                view.errorDisCountName("")
                true
            }
        }
    }

    override fun clickBack() {
        view.openPrevScreen()
    }

    override fun setBitmap(bitmap: Bitmap) {
        this.bitmap = bitmap
        checkFormValidity()
    }

    override fun clickSelectImg() {
        view.openGallery()
    }

    private fun checkFormValidity() {
        view.enableBtn(isDescriptionValid && isPriceValid && isNameValid && bitmap != null)
    }
}
