package com.example.sqlitedemo1

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter

import com.example.sqlitedemo1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseHelper:Databasehelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        databaseHelper = Databasehelper(this)

        with(binding) {
            insert.setOnClickListener {
                val name = userName.text.toString()
                val age = userAge.text.toString().toInt()
                val hobby=hobby.text.toString()
                databaseHelper.insertUser(name, age,hobby)
            }

            delete.setOnClickListener{
                val id= id.text.toString().toInt()
                databaseHelper.deleteUser(id)
            }

            update.setOnClickListener {
                val id=id.text.toString().toInt()
                val name=userName.text.toString()
                val age=userAge.text.toString().toInt()
                databaseHelper.updateUser(User(id,name,age))
            }

            fetch.setOnClickListener{
                val users = databaseHelper.fetchUser()
                val arrayAdapter =
                    ArrayAdapter(this@MainActivity, R.layout.simple_list_item_1,users)
                userList.adapter = arrayAdapter
            }
        }
    }
}