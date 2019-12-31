package aj.navigationcomponents.home_module


import aj.navigationcomponents.MainActivity
import aj.navigationcomponents.R
import aj.navigationcomponents.base.BaseFragment
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout.*
import kotlinx.android.synthetic.main.layout.nameTV

class ProfileScreen : BaseFragment() {
    private var sheetBehavior: BottomSheetBehavior<LinearLayout>? = null
    private var btnBottomSheet: Button? = null
    private var lineraLL: LinearLayout? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.profile_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).toolbar.visibility = View.VISIBLE
        (activity as MainActivity).setToolbar("Profile", R.drawable.ic_menu, true, false, true)
        lineraLL = view.findViewById(R.id.bottom_sheet)
        sheetBehavior = BottomSheetBehavior.from(lineraLL)
        setOnClickListener()

        var email=  baseActivity?.prefStore?.getString("email")
        var name=  baseActivity?.prefStore?.getString("name")
        var dob=  baseActivity?.prefStore?.getString("dob")
        var phnNum=  baseActivity?.prefStore?.getString("mob")
        var address=  baseActivity?.prefStore?.getString("address")
        nameTV.text =email+"\n" +name+"\n"+dob+"\n"+phnNum+"\n"+address


        //setBottomSheetCallback
        sheetBehavior?.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(p0: View, p1: Float) {

            }

            @SuppressLint("SwitchIntDef")
            override fun onStateChanged(p0: View, p1: Int) {
                when (p1) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        btnBottomSheet?.text = "Update"
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        btnBottomSheet?.text = "Edit"
                    }
                    BottomSheetBehavior.STATE_DRAGGING, BottomSheetBehavior.STATE_SETTLING -> sheetBehavior!!.setHideable(
                        false
                    )
                }
            }

        })

    }


    //region Helper methods for OnClick
    private fun setOnClickListener() {
        btn_bottom_sheet?.setOnClickListener {
            if (sheetBehavior?.state != BottomSheetBehavior.STATE_EXPANDED) {
                sheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
                btnBottomSheet?.text = "Update"
            } else {
                sheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
                btnBottomSheet?.text = "Edit"
            }
        }
    }
    //endregion

}
