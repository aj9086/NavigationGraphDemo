package aj.navigationcomponents.base

import aj.navigationcomponents.utils.PrefStore
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment(), View.OnClickListener {

    var baseActivity: BaseActivity? = null
    var prefStore: PrefStore? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefStore = PrefStore(context!!!!)
    }

    override fun onStart() {
        super.onStart()
        prefStore = PrefStore(context!!!!)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context!!)
        baseActivity = context as BaseActivity
    }
    override fun onClick(p0: View?) {

    }
}