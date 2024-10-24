package uz.gita.latizx.onlinemarketexam6.ui.personal

import uz.gita.latizx.onlinemarketexam6.models.UserModel
import uz.gita.latizx.onlinemarketexam6.source.pref.MyPref

class PersonalInfoModel : PersonalInfoContract.Model {
    private val pref = MyPref.getInstance()

    override fun getUserData(): UserModel = pref.getUser()

    override fun saveUserData(userModel: UserModel) {
        pref.setUser(userModel)
    }

    override fun deleteUser() {
        pref.deleteUser()
    }
}