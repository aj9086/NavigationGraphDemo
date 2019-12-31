package aj.navigationcomponents.home_module


import aj.navigationcomponents.MainActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import aj.navigationcomponents.R
import android.widget.TextView
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.detail_screen.*


class DetailScreen : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.detail_screen, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).toolbar.visibility=View.VISIBLE
        (activity as MainActivity).setToolbar("Detail", R.drawable.ic_back,
            isBackShown = true,
            isBottomBarShown = false,
            isDrawerShown = false
        )

        val name = arguments?.getString("userName") ?: "AJ"
             nameTV.text = name

    }

}
