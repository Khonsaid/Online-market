package uz.gita.latizx.onlinemarketexam6.ui.personal

import uz.gita.latizx.onlinemarketexam6.models.UserModel

class PersonalInfoPresenter(private val view: PersonalInfoContract.View) : PersonalInfoContract.Presenter {
    private val model: PersonalInfoContract.Model = PersonalInfoModel()
    private val user = model.getUserData()
    private var isFullNameValid = true
    private var isAgeValid = true
    private var isPhoneValid = true
    private var isNickNameValid = true
    private var isPasswordValid = true

    private var isFullNameChanged = false
    private var isAgeChanged = false
    private var isPhoneChanged = false
    private var isNickNameChanged = false
    private var isPasswordChanged = false

    init {
        view.loadData(user)
    }

    override fun clickDeleteUser() {
        view.showDeleteDialog()
    }

    override fun deleteUser() {
        model.deleteUser()
        view.openLoginScreen()
    }

    override fun clickSaveBtn(fullName: String, age: String, phone: String, nickName: String, password: String) {
        if (validateAllFields(fullName, age, phone, nickName, password)) {
            model.saveUserData(UserModel(fullName, age.toInt(), phone, nickName, password))
            view.showSuccessDialogAndOpenPrevScreen()
        }
    }

    override fun clickBack() {
        view.openPrevScreen()
    }

    override fun setFullNameListener(fullName: String) {
        isFullNameChanged = fullName != user.fullName
        isFullNameValid = when {
            fullName.isEmpty() -> {
                view.errorFullName("Full name cannot be empty!")
                false
            }

            fullName.length < 3 -> {
                view.errorFullName("Full name must be longer than 3 characters!")
                false
            }

            else -> {
                view.errorFullName("")
                true
            }
        }
        checkFormValidity()
    }

    override fun setAgeListener(age: String) {
        if (age.isBlank() || age.isEmpty()) return
        isAgeChanged = age != user.age.toString()
        isAgeValid = when {
            age.toInt() < 11 -> {
                view.errorAge("Age must be at least 11")
                false
            }

            age.toInt() > 79 -> {
                view.errorAge("Age must be less than 80")
                false
            }

            else -> {
                view.errorAge("")
                true
            }
        }
        checkFormValidity()
    }

    override fun setPhoneListener(phone: String) {
        isPhoneChanged = phone != user.phoneNumber
        isPhoneValid = when {
            !phone.matches(".*[0-9].*".toRegex()) -> {
                view.errorPhone("Phone number must contain only digits")
                false
            }

            !phone.startsWith("998") -> {
                view.errorPhone("Phone number must start with 998")
                false
            }

            else -> {
                view.errorPhone("")
                true
            }
        }
        checkFormValidity()
    }

    override fun setNickNameListener(nickName: String) {
        isNickNameChanged = nickName != user.nickName

        isNickNameValid = when {
            nickName.isEmpty() -> {
                view.errorNickName("Nickname cannot be empty!")
                false
            }

            nickName.length < 3 -> {
                view.errorNickName("Nickname must be longer than 3 characters!")
                false
            }

            else -> {
                view.errorNickName("")
                true
            }
        }

        checkFormValidity()
    }

    override fun setPasswordListener(password: String) {
        isPasswordChanged = password != user.password
        isPasswordValid = when {
            password.isBlank() -> {
                view.errorPassword("Password cannot be empty!")
                false
            }

            password.length < 6 -> {
                view.errorPassword("Password must be longer than 6 characters!")
                false
            }

            !password.matches(".*[A-Z].*".toRegex()) -> {
                view.errorPassword("Password must contain at least one uppercase letter!")
                false
            }

            !password.matches(".*[a-z].*".toRegex()) -> {
                view.errorPassword("Password must contain at least one lowercase letter!")
                false
            }

            !password.matches(".*[0-9].*".toRegex()) -> {
                view.errorPassword("Password must contain at least one number!")
                false
            }

            else -> {
                view.errorPassword("")
                true
            }
        }
        checkFormValidity()
    }

    private fun checkFormValidity() {
        val isChanged = isFullNameChanged || isAgeChanged || isPhoneChanged || isNickNameChanged || isPasswordChanged
        view.enableBtn(isChanged && isFullNameValid && isAgeValid && isPhoneValid && isNickNameValid && isPasswordValid)
    }

    private fun validateAllFields(fullName: String, age: String, phone: String, nickName: String, password: String): Boolean {
        setFullNameListener(fullName)
        setAgeListener(age)
        setPhoneListener(phone)
        setNickNameListener(nickName)
        setPasswordListener(password)

        return isFullNameValid && isAgeValid && isPhoneValid && isNickNameValid && isPasswordValid
    }
}