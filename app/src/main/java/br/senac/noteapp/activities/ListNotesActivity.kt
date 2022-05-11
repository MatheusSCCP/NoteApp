package br.senac.noteapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import br.senac.noteapp.data.AppDatabase
import br.senac.noteapp.data.Note
import br.senac.noteapp.data.NoteObject
import br.senac.noteapp.databinding.ActivityListNotesBinding
import br.senac.noteapp.databinding.CardNoteBinding

class ListNotesActivity : AppCompatActivity() {
    lateinit var binding: ActivityListNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.fab.setOnClickListener() {
            val i = Intent(this, NewNoteActivity::class.java)
            startActivity(i)
        }
    }

    fun OnResume() {
        super.onResume()
        updatesNotes()
    }
    fun updatesNotes(){
        Thread{
            val db = Room.databaseBuilder(this, AppDatabase::class.java, "db").build()
            val list = db.noteDao().listAll()
            //Roda o codigo da tela
            runOnUiThread {
                updateUI(list)
            }
        }.start()
    }

    fun updateUI(note: List<Note>) {
        binding.container.removeAllViews()
           //Atualiza a tela
            note.forEach {
            val card = CardNoteBinding.inflate(layoutInflater)

            card.textTitle.text = it.title
            card.textTitle.text = it.desc

            binding.container.addView(card.root)
        }
    }

}