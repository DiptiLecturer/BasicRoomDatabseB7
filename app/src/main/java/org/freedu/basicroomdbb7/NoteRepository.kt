package org.freedu.basicroomdbb7

class NoteRepository(private val noteDao: NoteDao) {


   suspend fun getAllNotesFromRepo(): List<Note>{
        return noteDao.getAllNotes()
    }
    suspend  fun insert(note: Note){
        noteDao.insert(note)
    }
    suspend fun update(note: Note){
        noteDao.update(note)
    }
    suspend fun delete(note: Note){
        noteDao.delete(note)
    }
    suspend fun searchNotesFromRepo(query: String): List<Note>{
        return noteDao.searchNotes(query)
    }


}