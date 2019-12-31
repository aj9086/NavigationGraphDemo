package aj.navigationcomponents.home_module


import aj.navigationcomponents.MainActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import aj.navigationcomponents.R
import aj.navigationcomponents.base.BaseFragment
import aj.navigationcomponents.home_module.notelist.ClickListener
import aj.navigationcomponents.home_module.notelist.NoteAdapter
import aj.navigationcomponents.home_module.notelist.NoteListViewModel
import aj.navigationcomponents.model.UserInfo
import aj.navigationcomponents.utils.Note
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_screen.*
import kotlinx.android.synthetic.main.note_item.*
import kotlinx.android.synthetic.main.note_list_fragment.*

/**
 * A simple [Fragment] subclass.
 */
class HomeScreen : BaseFragment() {
  /*  var userInfo : UserInfo? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).toolbar.visibility=View.VISIBLE
        (activity as MainActivity).setToolbar("Home", R.drawable.ic_menu,true,true,true)
//        baseActivity?.prefStore?.restoreUserInfo()
//        userInfo= arguments?.getParcelable("userInfo")

        var email=  baseActivity?.prefStore?.getString("email")
        var name=  baseActivity?.prefStore?.getString("name")
        var dob=  baseActivity?.prefStore?.getString("dob")
        var phnNum=  baseActivity?.prefStore?.getString("mob")
        var address=  baseActivity?.prefStore?.getString("address")
        nameTV.text =email+"\n" +name+"\n"+dob+"\n"+phnNum+"\n"+address

//        nameTV.text = arguments?.let { HomeScreenArgs.fromBundle(it).name}

    }*/
  private val clickListener: ClickListener = this::onNoteClicked

    private val recyclerViewAdapter = NoteAdapter(clickListener)

    private lateinit var viewModel: NoteListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.note_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).toolbar.visibility=View.VISIBLE
        (activity as MainActivity).setToolbar("Home", R.drawable.ic_menu,true,true,true)
        setupRecyclerView()

        viewModel = ViewModelProviders.of(this).get(NoteListViewModel::class.java)
        viewModel.observableNoteList.observe(this, Observer { notes ->
            notes?.let { render(notes) }
        })

        fab.setOnClickListener {
            findNavController(this).navigate(R.id.action_homeScreen_to_addNoteFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.load()
    }

    private fun render(noteList: List<Note>) {
        recyclerViewAdapter.updateNotes(noteList)
        if (noteList.isEmpty()) {
            notesRecyclerView.visibility = View.GONE
            notesNotFound.visibility = View.VISIBLE
        } else {
            notesRecyclerView.visibility = View.VISIBLE
            notesNotFound.visibility = View.GONE
        }
    }

    private fun onNoteClicked(note: Note) {

//        val bundle =Bundle()
//        bundle.putInt("noteId",note.id)
//        findNavController(this).navigate(R.id.action_homeScreen_to_noteDetailFragment,bundle)
        val navDirections = HomeScreenDirections.actionHomeScreenToNoteDetailFragment(note.id)
        view?.let {
            findNavController(this).navigate(navDirections)
        }
    }

    private fun setupRecyclerView() {
        notesRecyclerView.layoutManager = LinearLayoutManager(this.context)
        notesRecyclerView.adapter = recyclerViewAdapter
        notesRecyclerView.setHasFixedSize(true)
    }

}
