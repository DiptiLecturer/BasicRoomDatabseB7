package org.freedu.basicroomdbb7

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao//Data Access Object
interface NoteDao {

    @Insert//to insert data in the table
    fun insert(note:Note)

    //to get data from the table-> select all from table
    @Query("SELECT * FROM notes")
    fun getAllNotes(): List<Note>



}