package uz.gita.latizx.onlinemarketexam6.ui.add_item

import uz.gita.latizx.onlinemarketexam6.source.room.AppDataBase

class AddItemPresenter(private val view: AddItemContract.View) : AddItemContract.Presenter {
    private val db = AppDataBase.instance
    private var isNameValid = false
    private var isDescriptionValid = false
    private var isPriceValid = false
    private var isDisCountValid = false

    override fun clickSaveBtn(name: String, description: String, price: String, discount: String, categoryId: Long) {

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
        isNameValid = when {
            description.isEmpty() -> {
                view.errorName("Description cannot be empty!")
                false
            }

            description.length < 3 -> {
                view.errorName("Description must be longer than 3 characters!")
                false
            }

            else -> {
                view.errorName("")
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
                view.errorPrice("")
                true
            }
        }
        checkFormValidity()
    }

    override fun resultImg() {

    }

    override fun clickBack() {
        view.openPrevScreen()
    }

    override fun clickSelectImg() {
        view.openGallery()
    }

    private fun checkFormValidity() {
        view.enableBtn(isDescriptionValid && isPriceValid && isNameValid)
    }
}
