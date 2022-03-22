package com.example.roomdatabaseknowledge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.roomdatabaseknowledge.roomobject.SingletonDatabase
import com.example.roomdatabaseknowledge.roomobject.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)

        val studentDAO = SingletonDatabase.getDatabase(this).studentDao()
        val flowStudent = studentDAO.getAll()
        flowStudent.observe(this){
            Toast.makeText(this, "One Activity" + it.map { student -> "ID: "+ student.id }.toString(), Toast.LENGTH_LONG).show()
        }


        findViewById<Button>(R.id.insert_student).setOnClickListener {
            lifecycleScope.launch {
                withContext(Dispatchers.IO){
                    studentDAO.insertAll(Student(firstName = "Do Thanh", lastName = "Nha", age = 24))
                }
            }
        }
    }
}