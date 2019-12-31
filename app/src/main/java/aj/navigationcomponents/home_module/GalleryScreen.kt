package aj.navigationcomponents.home_module


import aj.navigationcomponents.MainActivity
import aj.navigationcomponents.R
import aj.navigationcomponents.base.BaseFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass.
 */
class GalleryScreen : BaseFragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.gallery_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).toolbar.visibility=View.VISIBLE
        (activity as MainActivity).setToolbar("Gallery", R.drawable.ic_menu,
            isBackShown = true,
            isBottomBarShown = true,
            isDrawerShown = true
        )


        viewAdapter = MyAdapter(arrayOf("Flo", "Ly", "Jo","Rose","Ipac"))

        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            adapter = viewAdapter

        }

    }

}

class MyAdapter(private val myDataset: Array<String>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(val item: View) : RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_view_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.item.findViewById<TextView>(R.id.user_name_text).text = myDataset[position]

        holder.item.findViewById<ImageView>(R.id.user_avatar_image)
            .setImageResource(listOfAvatars[position])

        holder.item.setOnClickListener {
            val bundle = bundleOf("userName" to myDataset[position])
            Navigation.findNavController(holder.item).navigate(
                R.id.action_galleryScreen2_to_detailScreen,
                bundle)
        }
    }

    override fun getItemCount() = myDataset.size
}

private val listOfAvatars = listOf(
    R.drawable.ic_menu_user, R.drawable.ic_menu_user, R.drawable.ic_menu_user, R.drawable.ic_menu_user, R.drawable.ic_menu_user)