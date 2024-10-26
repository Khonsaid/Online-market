package uz.gita.latizx.onlinemarketexam6.ui.main

import uz.gita.latizx.onlinemarketexam6.source.room.entity.ItemEntity
import uz.gita.latizx.onlinemarketexam6.utils.toKarzinaEntity

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private val model: MainContract.Model = MainModel()

    init {
        view.loadAdvertising(model.getAdvertisingList())
        view.loadItems(model.getAllItem().shuffled())
        view.showPlaceHolder(model.getAllItem().isEmpty())
    }

    override fun clickLike(itemEntity: ItemEntity) {
        if (model.updateItem(itemEntity.copy(favourite = if (itemEntity.favourite == 0) 1 else 0)) > 0) {
            view.loadItems(model.getAllItem())
        }
    }

    override fun clickBuy(itemEntity: ItemEntity) {
        model.addItemInKarzina(itemEntity.toKarzinaEntity(1))
        view.showToast(itemEntity)
    }

    override fun filterItems(query: String?) {
        val filterList = if (!query.isNullOrEmpty()) {
            view.showImageSlider(true)
            model.getAllItem().filter { item ->
                item.name.contains(query, ignoreCase = true)
            }
        } else {
            view.showImageSlider(false)
            model.getAllItem()
        }
        view.showPlaceHolder(filterList.isEmpty())
        view.loadItems(filterList)
    }

    override fun clickFavouriteBtn() {
        view.openNextScreen()
    }

    override fun clickItem(itemId: Long) {
        view.openInfoScreen(itemId)
    }
}