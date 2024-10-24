package uz.gita.latizx.onlinemarketexam6.ui.profil

import uz.gita.latizx.onlinemarketexam6.data.ProfileData
import uz.gita.latizx.onlinemarketexam6.models.UserModel

interface ProfileContract {
    interface Model {
        fun getProfileData(): List<ProfileData>
        fun getUser(): UserModel
    }

    interface View {
        fun openProfileInfoScreen()
        fun showToast(message: String)
        fun loadData(list: List<ProfileData>)
        fun loadUserData(userModel: UserModel)
    }

    interface Presenter {
        fun clickItem(name: String)
        fun clickLogOut()
    }
}