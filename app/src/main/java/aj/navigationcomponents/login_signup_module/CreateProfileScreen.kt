package aj.navigationcomponents.login_signup_module


import aj.navigationcomponents.MainActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import aj.navigationcomponents.R
import aj.navigationcomponents.base.BaseFragment
import aj.navigationcomponents.model.UserInfo
import android.util.Log
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.create_profile_screen.*

/**
 * A simple [Fragment] subclass.
 */
class CreateProfileScreen : BaseFragment() {

    var userInfo : UserInfo? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.create_profile_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).toolbar.visibility=View.VISIBLE
        (activity as MainActivity).setToolbar("Create Profile", R.drawable.ic_back,true,false,false)
        if (arguments!=null)
//        userInfo= arguments?.getParcelable("userInfo")
            userInfo = arguments?.getSerializable("userInfo") as UserInfo?
        Log.e("tag","email>>>"+ arguments?.getString("email"))
        Log.e("tag","userInfo>>>"+arguments?.getSerializable("userInfo") as UserInfo?)
//        baseActivity?.prefStore?.restoreUserInfo()
    /*    userInfo?.name= nameET.text.toString()
        userInfo?.dob= dobET.text.toString()
        userInfo?.phnNum= mobET.text.toString()
        userInfo?.address= addressTV.text.toString()*/
//        baseActivity?.prefStore?.setUserInfo(userInfo!!)

        Log.e("tag","email pref>>>"+  baseActivity?.prefStore?.getString("email"))
        submitBT.setOnClickListener {

            baseActivity?.prefStore?.saveString("name",nameET.text.toString())
            baseActivity?.prefStore?.saveString("dob",dobET.text.toString())
            baseActivity?.prefStore?.saveString("mob",mobET.text.toString())
            baseActivity?.prefStore?.saveString("address",addressTV.text.toString())

       /*     userInfo?.name= nameET.text.toString()
            userInfo?.dob= dobET.text.toString()
            userInfo?.phnNum= mobET.text.toString()
            userInfo?.address= addressTV.text.toString()
           baseActivity?.prefStore?.storeUserInfo(userInfo!!)*/

//            val bundle = Bundle()
//            bundle.putParcelable("userInfo",userInfo)

//            val bundle = bundleOf("userInfo" to userInfo!!)
//            Navigation.findNavController(it).navigate(R.id.action_createProfileScreen_to_homeScreen,bundle)
            Navigation.findNavController(it).navigate(R.id.action_createProfileScreen_to_homeScreen)
        }
    }
}
