package uz.gita.latizx.onlinemarketexam6.ui.login

import uz.gita.latizx.onlinemarketexam6.models.UserModel

interface LoginContract {
    interface Model {
        fun saveUser(userModel: UserModel)
    }

    interface View {
        fun showSuccessDialogAndOpenHomeScreen()
        fun enableBtn(isEnable: Boolean)

        fun errorFullName(error: String)
        fun errorAge(error: String)
        fun errorPhone(error: String)
        fun errorNickName(error: String)
        fun errorPassword(error: String)
    }

    interface Presenter {

        fun clickSaveBtn(fullName: String, age: String, phone: String, nickName: String, password: String)

        fun setFullNameListener(fullName: String)
        fun setAgeListener(age: String)
        fun setPhoneListener(phone: String)
        fun setNickNameListener(nickName: String)
        fun setPasswordListener(password: String)
    }
}