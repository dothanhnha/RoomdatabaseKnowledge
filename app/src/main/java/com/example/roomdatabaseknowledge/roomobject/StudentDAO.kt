package com.example.roomdatabaseknowledge.roomobject

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentDao {
    @Query("SELECT * FROM Student")
    fun getAll(): LiveData<List<Student>>

    @Query("SELECT * FROM Student WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): LiveData<List<Student>>

    @Query("SELECT * FROM Student WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): LiveData<Student>

    @Insert
    fun insertAll(vararg students: Student)

    @Delete
    fun delete(student: Student)
}