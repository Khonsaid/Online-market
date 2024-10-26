package uz.gita.latizx.onlinemarketexam6.ui.basket

import android.util.Log
import uz.gita.latizx.onlinemarketexam6.source.room.entity.KarzinaEntity

class BasketPresenter(private val view: BasketContract.View) : BasketContract.Presenter {
    private val model: BasketContract.Model = BasketModel()

    init {
        loadData()
    }

    override fun clickPlus(karzinaEntity: KarzinaEntity) {
        val count = karzinaEntity.count
        model.updateItemKorzina(karzinaEntity.copy(count = count + 1))
        loadData()
    }

    override fun clickMinus(karzinaEntity: KarzinaEntity) {
        val count = karzinaEntity.count
        Log.d("TTT", "clickMinus: $count")
        if (count > 1) {
            model.updateItemKorzina(karzinaEntity.copy(count = count - 1))
            loadData()
        }
    }

    override fun clickLongMinus(karzinaEntity: KarzinaEntity) {
        val count = karzinaEntity.count
        Log.d("TTT", "clickLongMinus: $count")
        if (count > 1) {
            model.updateItemKorzina(karzinaEntity.copy(count = 1))
            loadData()
        }
    }

    override fun clickRemove(karzinaEntity: KarzinaEntity) {
        view.showRemoveDialog(karzinaEntity)
    }

    override fun clickBuy() {
        model.removeAll()
        view.showSuccessDialog()
        loadData()
    }

    override fun removeItem(karzinaEntity: KarzinaEntity) {
        model.removeKorzina(karzinaEntity)
        loadData()
    }

    private fun loadData() {
        if (model.getAllItem().isNotEmpty()) {
            view.submitList(model.getAllItem())
            view.loadData(
                model.getAllItem(),
                model.getTotalSum() * model.getCountKorzina(),
                model.getTotalSumWithDiscount() * model.getCountKorzina(),
                model.getCountKorzina()
            )
            view.showPlaceHolder(false)
        } else {
            view.showPlaceHolder(true)
        }
    }
}