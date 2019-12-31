package aj.navigationcomponents.home_module.notedetail

import aj.navigationcomponents.MainActivity
import aj.navigationcomponents.R
import aj.navigationcomponents.base.BaseFragment
import aj.navigationcomponents.home_module.notedetail.NoteDetailFragmentArgs.Companion.fromBundle
import aj.navigationcomponents.utils.Note
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.note_detail_fragment.*

class NoteDetailFragment : BaseFragment() {

    private lateinit var viewModel: NoteDetailViewModel

    private val noteId by lazy {
        arguments?.let { fromBundle(it).noteId }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.note_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).toolbar.visibility = View.VISIBLE
        (activity as MainActivity).setToolbar(
            "Note Detatil",
            R.drawable.ic_back,
            true,
            false,
            false
        )

        viewModel = ViewModelProviders.of(this).get(NoteDetailViewModel::class.java)
        viewModel.observableNote.observe(this, Observer { note ->
                        note?.let { render(note) } ?: renderNoteNotFound()
        })

        editNoteButton.setOnClickListener {
                        val navDirections = noteId?.let { it1 ->
                NoteDetailFragmentDirections.actionNoteDetailFragmentToEditNote(
                    it1
                )
            }
            if (navDirections != null) {
                findNavController(it).navigate(navDirections)
            }
        }

        deleteNoteButton.setOnClickListener {
                        val navDirections = noteId?.let { it1 ->
                NoteDetailFragmentDirections.actionNoteDetailFragmentToDeleteNote(
                    it1
                )
            }
            navDirections?.let { it1 -> findNavController(it).navigate(it1) }
        }
        }

    override fun onResume() {
        super.onResume()
        noteId?.let { viewModel.getNote(it) }
    }

    private fun render(note: Note) {
//        noteIdView.text = String.format("id : ", note.id)
        noteIdView.text ="id : "+note.id
//        noteText.text = String.format("text : ", note.text)
        noteText.text = "text : "+ note.text
    }

    private fun renderNoteNotFound() {
        noteIdView.visibility = View.GONE
        noteText.visibility = View.GONE
        view?.let {
            Snackbar.make(it,"error loading note", Snackbar.LENGTH_LONG).show()
        }
    }
}