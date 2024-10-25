package uz.gita.latizx.onlinemarketexam6.ui.main

import com.denzcoskun.imageslider.models.SlideModel
import uz.gita.latizx.onlinemarketexam6.R

class MainModel : MainContract.Model {
    override fun getAdvertisingList(): ArrayList<SlideModel> = loadAdvertising()

    private fun loadAdvertising(): ArrayList<SlideModel> =
        arrayListOf(
            SlideModel(R.drawable.img_advertising5, ""),
            SlideModel(R.drawable.img_advertising3, ""),
            SlideModel(R.drawable.img_advertising2, ""),
            SlideModel(R.drawable.img_advertising1, ""),
            SlideModel(R.drawable.img_advertising4, ""),
        )
}