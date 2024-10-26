package uz.gita.latizx.onlinemarketexam6.ui.info

import android.util.Log
import uz.gita.latizx.onlinemarketexam6.utils.toKarzinaEntity

class InfoPresenter(private val view: InfoContract.View, private val itemId: Long) : InfoContract.Presenter {
    private val model: InfoContract.Model = InfoModel()
    private var itemData = model.getItemById(itemId)[0]

    init {
        view.loadData(itemData)
    }

    override fun clickBack() {
        view.openPrevScreen()
    }

    override fun clickAddCart() {
        model.addToCard(itemData.toKarzinaEntity(1))
        view.showToast(itemData)
        view.openCartScreen()
    }

    override fun clickGoToCart() {
        view.openCartScreen()
    }

    override fun updateItem() {
        model.updateItem(itemData.copy(favourite = if (itemData.favourite == 0) 1 else 0))
        itemData = model.getItemById(itemId)[0]
        view.changeLike(itemData.favourite)
    }
}