package uz.gita.latizx.onlinemarketexam6.ui.login

import uz.gita.latizx.onlinemarketexam6.models.UserModel

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {
    private val model: LoginContract.Model = LoginModel()

    private var isFullNameValid = false
    private var isAgeValid = false
    private var isPhoneValid = false
    private var isNickNameValid = false
    private var isPasswordValid = false

    override fun clickSaveBtn(fullName: String, age: String, phone: String, nickName: String, password: String) {
        if (validateAllFields(fullName, age, phone, nickName, password)) {
            model.saveUser(UserModel(fullName, age.toInt(), phone, nickName, password))
            view.showSuccessDialogAndOpenHomeScreen()
        }
    }

    override fun setFullNameListener(fullName: String) {
        isFullNameValid = when {
            fullName.isEmpty() -> {
                view.errorFullName("To'liq ism bo'sh bo'lishi mumkin emas!")
                false
            }

            fullName.length < 3 -> {
                view.errorFullName("To'liq ism 3 ta belgidan oshmasligi kerak!")
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
        isAgeValid = when {
            age.toInt() < 11 -> {
                view.errorAge("Yoshi kamida 11 bo'lishi kerak")
                false
            }

            age.toInt() > 79 -> {
                view.errorAge("Yoshi 80 dan kam bo'lishi")
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
        isPhoneValid = when {
            !phone.matches(".*[0-9].*".toRegex()) -> {
                view.errorPhone("Faqat raqamlardan iborat bo'lishi kerak")
                false
            }

            !phone.startsWith("998") -> {
                view.errorPhone("Telefon raqami 998 bilan boshlanishi kerak")
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
        isNickNameValid = when {
            nickName.isEmpty() -> {
                view.errorNickName("Nickname bo'sh bo'lishi mumkin emas!")
                false
            }

            nickName.length < 3 -> {
                view.errorNickName("Nickname 3 ta belgidan oshmasligi kerak!!")
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
        isPasswordValid = when {
            password.isBlank() -> {
                view.errorPassword("Parol bo'sh bo'lishi mumkin emas!")
                false
            }

            password.length < 6 -> {
                view.errorPassword("Parol 6 ta belgidan oshmasligi kerak!")
                false
            }

            !password.matches(".*[A-Z].*".toRegex()) -> {
                view.errorPassword("Parol kamida bitta katta harfdan iborat bo'lishi kerak!")
                false
            }

            !password.matches(".*[a-z].*".toRegex()) -> {
                view.errorPassword("Parol kamida bitta kichik harfdan iborat bo'lishi kerak!")
                false
            }

            !password.matches(".*[0-9].*".toRegex()) -> {
                view.errorPassword("Parolda kamida bitta raqam bo'lishi kerak!")
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
        view.enableBtn(isFullNameValid && isAgeValid && isPhoneValid && isNickNameValid && isPasswordValid)
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