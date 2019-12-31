package aj.navigationcomponents.login_signup_module


import aj.navigationcomponents.MainActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import aj.navigationcomponents.R
import aj.navigationcomponents.base.BaseFragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login_screen.*

/**
 * A simple [Fragment] subclass.
 */
class LoginScreen : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).toolbar.visibility=View.VISIBLE
        (activity as MainActivity).setToolbar("Login",0,false,false,false)

        nextBT.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loginScreen_to_homeScreen)
        }
        forgotTV.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loginScreen_to_forgotPassScreen)
        }
        signupTV.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loginScreen_to_signupScreen)
        }
    }

}
