package uz.gita.latizx.onlinemarketexam6.ui.catalog

import uz.gita.latizx.onlinemarketexam6.source.room.entity.CategoryEntity

class CategoryPresenter(private val view: CategoryContract.View) : CategoryContract.Presenter {
    private val model: CategoryContract.Model = CategoryModel()
    private var list = model.getAllCategory()

    init {
        view.loadData(list)
    }

    override fun clickAddBtn() {
        view.showDialogAdd(model.getImgCategory())
    }

    override fun clickItem(categoryEntity: CategoryEntity) {
        view.openItemByCategory(categoryEntity)
    }

    override fun addCategory(categoryEntity: CategoryEntity) {
        val idCategory = model.saveCategory(categoryEntity)
        if (idCategory > 0) {
            list.add(categoryEntity.copy(id = idCategory))
            view.notifyItemChanged(list.lastIndex)
            view.showSuccessDialog()
        }
    }
}