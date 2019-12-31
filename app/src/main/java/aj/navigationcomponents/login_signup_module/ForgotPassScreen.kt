package aj.navigationcomponents.login_signup_module


import aj.navigationcomponents.MainActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import aj.navigationcomponents.R
import aj.navigationcomponents.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass.
 */
class ForgotPassScreen : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.forgot_pass_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).toolbar.visibility=View.VISIBLE
        (activity as MainActivity).setToolbar("Forgot Password", R.drawable.ic_back,true,false,false)

    }
}
