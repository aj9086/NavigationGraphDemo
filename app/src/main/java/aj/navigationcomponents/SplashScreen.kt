package aj.navigationcomponents


import aj.navigationcomponents.base.BaseFragment
import aj.navigationcomponents.login_signup_module.LoginScreen
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.constraintlayout.solver.GoalRow
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass.
 */
class SplashScreen : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.splash_screen, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        (activity as MainActivity).window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        (activity as MainActivity).toolbar.visibility=View.GONE
        (activity as MainActivity).setToolbar("",0,
            isBackShown = false,
            isBottomBarShown = false,
            isDrawerShown = false
        )

        Handler().postDelayed({
            context?.let {
             findNavController(this).navigate(R.id.action_splashScreen_to_loginScreen)

//                Toast.makeText(it,"delay done",Toast.LENGTH_LONG).show()
            }
        }, 2500)
    }

}
