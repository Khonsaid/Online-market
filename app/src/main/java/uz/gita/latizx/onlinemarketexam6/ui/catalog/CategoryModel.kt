package uz.gita.latizx.onlinemarketexam6.ui.catalog

import uz.gita.latizx.onlinemarketexam6.R
import uz.gita.latizx.onlinemarketexam6.source.room.AppDataBase
import uz.gita.latizx.onlinemarketexam6.source.room.entity.CategoryEntity

class CategoryModel : CategoryContract.Model {
    private val db = AppDataBase.instance.categoryDao()
    override fun getAllCategory(): ArrayList<CategoryEntity> = ArrayList(db.getAllCategory())
    override fun getImgCategory(): List<Int> = loadImg()

    override fun saveCategory(categoryEntity: CategoryEntity) = db.addCategory(categoryEntity)

    private fun loadImg(): List<Int> {
        val list: ArrayList<Int> = arrayListOf(R.drawable.ic_logo_category)
        list.addAll(
            listOf(
                R.drawable.ic_category_car,
                R.drawable.ic_category_books,
                R.drawable.ic_category_hause,
                R.drawable.ic_category_kiyim,
                R.drawable.ic_category_stop,
                R.drawable.ic_category_poyabzal,
                R.drawable.ic_category_animals,
                R.drawable.ic_category_tomorqa,
                R.drawable.ic_category_health,
                R.drawable.ic_category_maishiy,
                R.drawable.ic_category_konselariya,
                R.drawable.ic_category_oziq_ovqat,
            ).shuffled()
        )
        return list
    }

}
