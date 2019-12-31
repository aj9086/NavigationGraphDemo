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
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login_screen.*
import kotlinx.android.synthetic.main.signup_screen.*

/**
 * A simple [Fragment] subclass.
 */
class SignupScreen : BaseFragment() {

    var userInfo : UserInfo ? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.signup_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).toolbar.visibility=View.VISIBLE
        (activity as MainActivity).setToolbar("Sign up",R.drawable.ic_back,true,false,false)

        signupBT.setOnClickListener {
            baseActivity?.prefStore?.saveString("email",emailET.text.toString())
//            baseActivity?.prefStore?.storeUserInfo(userInfo!!)
            val bundle = Bundle()
            bundle.putString("email",emailET.text.toString())
//            bundle.putParcelable("userInfo",userInfo)
            userInfo?.email= emailET.text.toString()
            userInfo?.password= passET.text.toString()
            bundle.putSerializable("userInfo",userInfo)
            Navigation.findNavController(it).navigate(R.id.action_signupScreen_to_createProfileScreen,bundle)
        }
    }

}
