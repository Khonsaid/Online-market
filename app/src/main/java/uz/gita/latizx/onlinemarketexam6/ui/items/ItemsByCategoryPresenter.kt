package uz.gita.latizx.onlinemarketexam6.ui.items

import uz.gita.latizx.onlinemarketexam6.source.room.entity.ItemEntity
import uz.gita.latizx.onlinemarketexam6.utils.toKarzinaEntity

class ItemsByCategoryPresenter(private val view: ItemsByCategoryContract.View) : ItemsByCategoryContract.Presenter {
    private val model: ItemsByCategoryContract.Model = ItemsByCategoryModel()
    private var categoryId: Long = -1

    override fun getCategoryId(categoryId: Long) {
        this.categoryId = categoryId
        view.lostData(model.getItemsByCategory(categoryId))
    }

    override fun clickBack() {
        view.openPrevScreen()
    }

    override fun clickLike(itemEntity: ItemEntity) {
        if (model.updateItem(itemEntity.copy(favourite = if (itemEntity.favourite == 0) 1 else 0)) > 0) {
            view.lostData(model.getItemsByCategory(categoryId))
        }
    }

    override fun clickAdd() {
        view.openAddScreen(categoryId)
    }

    override fun clickBuy(itemEntity: ItemEntity) {
        model.addItemInKarzina(itemEntity.toKarzinaEntity(1))
        view.showToast(itemEntity)
    }
}