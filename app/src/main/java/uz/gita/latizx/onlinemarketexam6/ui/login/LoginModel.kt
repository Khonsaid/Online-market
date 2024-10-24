package uz.gita.latizx.onlinemarketexam6.ui.login

import uz.gita.latizx.onlinemarketexam6.models.UserModel
import uz.gita.latizx.onlinemarketexam6.source.pref.MyPref

class LoginModel : LoginContract.Model {
    private val pref = MyPref.getInstance()
    override fun saveUser(userModel: UserModel) {
        pref.setUser(userModel)
    }
}