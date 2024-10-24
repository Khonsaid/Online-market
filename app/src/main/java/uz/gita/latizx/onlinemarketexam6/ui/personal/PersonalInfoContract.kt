package uz.gita.latizx.onlinemarketexam6.ui.personal

import uz.gita.latizx.onlinemarketexam6.models.UserModel

interface PersonalInfoContract {
    interface Model {
        fun getUserData(): UserModel
        fun saveUserData(userModel: UserModel)
        fun deleteUser()
    }

    interface View {
        fun loadData(data: UserModel)
        fun showSuccessDialogAndOpenPrevScreen()
        fun showDeleteDialog()
        fun openPrevScreen()
        fun openLoginScreen()
        fun enableBtn(isEnable: Boolean)

        fun errorFullName(error: String)
        fun errorAge(error: String)
        fun errorPhone(error: String)
        fun errorNickName(error: String)
        fun errorPassword(error: String)
    }

    interface Presenter {
        fun clickDeleteUser()
        fun deleteUser()
        fun clickSaveBtn(fullName: String, age: String, phone: String, nickName: String, password: String)
        fun clickBack()

        fun setFullNameListener(fullName: String)
        fun setAgeListener(age: String)
        fun setPhoneListener(phone: String)
        fun setNickNameListener(nickName: String)
        fun setPasswordListener(password: String)
    }
}