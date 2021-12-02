package com.julio.greennotes.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.julio.greennotes.model.Task


@Database(version = 1, entities = [Task::class], exportSchema = false)
abstract class GreenNotesDataBase: RoomDatabase() {

    abstract fun dao(): Dao

    companion object{
        fun getDataBaseInstance(context: Context) : GreenNotesDataBase{
            return Room.databaseBuilder(context, GreenNotesDataBase::class.java, "GreenNotesDatabase")
                .build()
        }
    }

}