package uz.gita.latizx.onlinemarketexam6.ui.basket

import uz.gita.latizx.onlinemarketexam6.source.room.entity.KarzinaEntity

interface BasketContract {
    interface Model {
        fun getAllItem(): List<KarzinaEntity>
        fun removeKorzina(karzinaEntity: KarzinaEntity)
        fun updateItemKorzina(karzinaEntity: KarzinaEntity)
        fun getTotalSum(): Int
        fun getCountKorzina(): Int
        fun getTotalSumWithDiscount(): Int
        fun removeAll()
    }

    interface View {
        fun submitList(list: List<KarzinaEntity>)
        fun showRemoveDialog(karzinaEntity: KarzinaEntity)
        fun showPlaceHolder(isVisible: Boolean)
        fun loadData(list: List<KarzinaEntity>, totalSum: Int, totalSumWithDiscount: Int,getCountKorzina:Int)
        fun showSuccessDialog()
    }

    interface Presenter {
        fun clickPlus(karzinaEntity: KarzinaEntity)
        fun clickMinus(karzinaEntity: KarzinaEntity)
        fun clickLongMinus(karzinaEntity: KarzinaEntity)
        fun clickRemove(karzinaEntity: KarzinaEntity)
        fun clickBuy()
        fun removeItem(karzinaEntity: KarzinaEntity)
    }
}