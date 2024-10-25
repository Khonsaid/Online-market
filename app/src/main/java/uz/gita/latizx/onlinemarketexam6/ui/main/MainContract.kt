package uz.gita.latizx.onlinemarketexam6.ui.main

import com.denzcoskun.imageslider.models.SlideModel

interface MainContract {
    interface Model {
        fun getAdvertisingList(): ArrayList<SlideModel>
    }

    interface View {
        fun loadAdvertising(list: ArrayList<SlideModel>)
    }

    interface Presenter {

    }
}