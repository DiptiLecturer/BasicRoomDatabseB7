package org.freedu.basicroomdbb7

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.freedu.basicroomdbb7.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
        db = AppDatabase.getDatabase(this)
        loadData()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    @SuppressLint("SetTextI18n")
    private fun loadData() {

        val list = db.noteDao().getAllNotes()

        binding.countTv.text="Total Notes : ${list.size}"

        val adapter = NoteAdapter(
            list,

            onEdit = { note ->
                val intent = Intent(this@HomeActivity, AddActivity::class.java)

                intent.putExtra("id",note.id)
                intent.putExtra("name",note.name)
                intent.putExtra("address",note.address)
                intent.putExtra("phone",note.phone)
                startActivity(intent)

            },
            onDelete = { note ->
                val view = layoutInflater.inflate(R.layout.dialog_delete, null)
                val dialog = MaterialAlertDialogBuilder(this)
                    .setView(view)
                    .create()

                view.findViewById<Button>(R.id.btnConfirmDelete).setOnClickListener {
                    db.noteDao().delete(note)
                    loadData()
                    dialog.dismiss()
                }

                dialog.show()
            }
            )

        binding.recyclerView.adapter = adapter

    }

}