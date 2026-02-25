package org.freedu.basicroomdbb7

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : NoteRepository

    val notesLiveData = MutableLiveData<List<Note>>()

    init {

        val dao = AppDatabase.getDatabase(application).noteDao()
        repository= NoteRepository(dao)
        loadNotesFromViewModel()
    }

     fun loadNotesFromViewModel() {
        notesLiveData.value = repository.getAllNotesFromRepo()
    }

    fun insertFromViewModel(note: Note){
        repository.insert(note)
        loadNotesFromViewModel()
    }
    fun updateFromViewModel(note: Note){
        repository.update(note)
        loadNotesFromViewModel()
    }
    fun deleteFromViewModel(note: Note){
        repository.delete(note)
        loadNotesFromViewModel()
    }



}