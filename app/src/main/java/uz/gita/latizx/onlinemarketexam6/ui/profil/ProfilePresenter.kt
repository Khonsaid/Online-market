package uz.gita.latizx.onlinemarketexam6.ui.profil

class ProfilePresenter(private val view: ProfileContract.View) : ProfileContract.Presenter {
    private val model: ProfileContract.Model = ProfileModel()

    init {
        view.loadData(model.getProfileData())
        view.loadUserData(model.getUser())
    }

    override fun clickItem(name: String) {
        if (name == "Profilim") view.openProfileInfoScreen()
        else view.showToast("Tez kunlarda!")
    }

    override fun clickLogOut() {
        view.showToast("Tez kunlarda!")
    }
}