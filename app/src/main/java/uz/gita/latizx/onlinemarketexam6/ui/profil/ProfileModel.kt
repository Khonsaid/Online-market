package uz.gita.latizx.onlinemarketexam6.ui.profil

import uz.gita.latizx.onlinemarketexam6.R
import uz.gita.latizx.onlinemarketexam6.data.ProfileData
import uz.gita.latizx.onlinemarketexam6.models.UserModel
import uz.gita.latizx.onlinemarketexam6.source.pref.MyPref

class ProfileModel : ProfileContract.Model {
    private val pref = MyPref.getInstance()
    override fun getProfileData(): List<ProfileData> = loadData()
    override fun getUser(): UserModel = pref.getUser()

    private fun loadData(): List<ProfileData> {
        return listOf(
            ProfileData(R.drawable.ic_user, "Profilim"),
            ProfileData(R.drawable.ic_order_light, "Buyurtmalarim"),
            ProfileData(R.drawable.ic_credit_card, "To'lov metodlari"),
            ProfileData(R.drawable.ic_notif, "Bildirishnomalar"),
            ProfileData(R.drawable.ic_privacy, "Maxfiylik siyosati"),
            ProfileData(R.drawable.ic_elements, "Ma'lumot"),
        )
    }
}