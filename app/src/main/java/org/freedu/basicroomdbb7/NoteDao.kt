package org.freedu.basicroomdbb7

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao//Data Access Object
interface NoteDao {

    @Insert//to insert data in the table
   suspend fun insert(note:Note)

    @Update//to Update data in the table
    suspend fun update(note: Note)

    @Delete//to delete data in the table
    suspend fun delete(note: Note)

    //to get data from the table-> select all from table
    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): List<Note>

    @Query("SELECT * from notes where name like '%' || :query || '%'")
    suspend fun searchNotes(query: String): List<Note>

}