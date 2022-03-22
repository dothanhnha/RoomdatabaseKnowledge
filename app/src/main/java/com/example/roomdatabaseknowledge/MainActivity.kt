package com.example.roomdatabaseknowledge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.example.roomdatabaseknowledge.roomobject.SingletonDatabase
import com.example.roomdatabaseknowledge.roomobject.Student
import com.example.roomdatabaseknowledge.roomobject.StudentRoomDatabase
import kotlinx.coroutines.*
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val studentDAO = SingletonDatabase.getDatabase(this).studentDao()
        val flowStudent = studentDAO.getAll()

        flowStudent.observe(this){
            Toast.makeText(this, "Main Activity" + it.map { student -> "ID: "+ student.id }.toString(), Toast.LENGTH_LONG).show()
        }


        findViewById<Button>(R.id.insert_delay_student).setOnClickListener {
            lifecycleScope.launch {
                withContext(Dispatchers.IO){
                    delay(3000)
                    studentDAO.insertAll(Student(firstName = "Do Thanh", lastName = "Nha", age = 24))
                }
            }
        }

        findViewById<Button>(R.id.insert_student).setOnClickListener {
            lifecycleScope.launch {
                withContext(Dispatchers.IO){
                    studentDAO.insertAll(Student(firstName = "Do Thanh", lastName = "Nha", age = 24))
                }
            }
        }

        findViewById<Button>(R.id.to_another_screen).setOnClickListener {
            startActivity(Intent(this, OneActivity::class.java))
        }
    }
}