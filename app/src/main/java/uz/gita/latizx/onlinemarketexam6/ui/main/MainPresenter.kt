package uz.gita.latizx.onlinemarketexam6.ui.main

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private val model: MainContract.Model = MainModel()

    init {
        view.loadAdvertising(model.getAdvertisingList())
    }

}