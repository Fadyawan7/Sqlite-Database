package com.example.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqlite.DatabaseHalperClass.DBHelperClass
import com.example.sqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter =MyAdapter(this,showData())

        binding.button.setOnClickListener {

            val text :String = binding.editText1.text.toString()
            addText(text)

            val text2 :String = binding.editText2.text.toString()
            addText(text2)

            binding.recyclerView.adapter =MyAdapter(this,showData())

        }



    }

    fun addText(text: String) {

        val databaseHalper = DBHelperClass(this)

        val status = databaseHalper.addData(ViewModal(0,text))

        if (status > -1){
            Toast.makeText(this, "Saves", Toast.LENGTH_SHORT).show()
        }else {
            Toast.makeText(this, "cannot be blank", Toast.LENGTH_LONG).show()
        }
    }



    fun showData(): ArrayList<ViewModal>{

        val databaseHalper = DBHelperClass(this)

        return databaseHalper.viewData()

    }
}