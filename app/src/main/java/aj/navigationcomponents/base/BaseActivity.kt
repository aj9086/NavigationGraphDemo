package aj.navigationcomponents.base

import aj.navigationcomponents.utils.PrefStore
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity(), View.OnClickListener {

    var prefStore: PrefStore? = null

    override fun onStart() {
        super.onStart()
        prefStore= PrefStore(this)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        prefStore= PrefStore(this)
    }
    override fun onClick(p0: View?) {

    }


}