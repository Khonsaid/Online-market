package uz.gita.latizx.onlinemarketexam6.ui.favourite

import uz.gita.latizx.onlinemarketexam6.source.room.entity.ItemEntity
import uz.gita.latizx.onlinemarketexam6.utils.toKarzinaEntity

class FavouritePresenter(private val view: FavouriteContract.View) : FavouriteContract.Presenter {
    private val model: FavouriteContract.Model = FavouriteModel()

    init {
        view.lostData(model.getAllFavourite().shuffled())
    }

    override fun clickLike(itemEntity: ItemEntity) {
        if (model.updateItem(itemEntity.copy(favourite = if (itemEntity.favourite == 0) 1 else 0)) > 0) {
            view.lostData(model.getAllFavourite())
        }
    }

    override fun clickBack() {
        view.openPrevScreen()
    }

    override fun clickBuy(itemEntity: ItemEntity) {
        model.addItemInKarzina(itemEntity.toKarzinaEntity(1))
        view.showToast(itemEntity)
    }

    override fun clickItem(itemId: Long) {
        view.openInfoScreen(itemId)
    }
}