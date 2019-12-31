package aj.navigationcomponents.home_module.deletenote

import aj.navigationcomponents.R
import aj.navigationcomponents.base.BaseFragment
import aj.navigationcomponents.home_module.deletenote.DeleteNoteFragmentArgs.Companion.fromBundle
import aj.navigationcomponents.utils.Note
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.delete_note_fragment.*

class DeleteNoteFragment : BaseFragment() {

    private lateinit var viewModel: DeleteNoteViewModel

    private val noteId by lazy {
        arguments?.let { fromBundle(it).noteId }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.delete_note_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DeleteNoteViewModel::class.java)
        viewModel.observableCurrentNote.observe(this, Observer { currentNote ->
            currentNote?.let { initCurrentNote(currentNote) }
        })
        viewModel.observableDeleteStatus.observe(this, Observer { deleteStatus ->
            deleteStatus?.let { render(deleteStatus) }
        })

        noteId?.let { viewModel.initNote(it) }

        cancelDeleteButton.setOnClickListener {
            findNavController(it).popBackStack()
        }

        confirmDeleteButton.setOnClickListener {
//            noteId?.let { it1 -> viewModel.deleteNote(it1) }
        }
    }

    private fun initCurrentNote(note: Note) {
        noteIdView.text = "id : "+note.id
        noteText.text = "text : "+ note.text
    }

    private fun render(deleteStatus: Boolean) {
        when (deleteStatus) {
            true -> {
                view?.let {
                    findNavController(it).popBackStack(R.id.deleteNoteFragment, false)
                }
            }
            false -> Snackbar.make(confirmDeleteButton, "error deleting note", Snackbar.LENGTH_LONG).show()
        }
    }
}