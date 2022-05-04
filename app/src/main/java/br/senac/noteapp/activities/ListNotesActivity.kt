package br.senac.noteapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        updateNotes()
    }

    fun updateNotes() {
        binding.container.removeAllViews()

        NoteObject.listNotes.forEach {
            val card = CardNoteBinding.inflate(layoutInflater)

            card.textTitle.text = it.title
            card.textTitle.text = it.desc

            binding.container.addView(card.root)
        }
    }

}