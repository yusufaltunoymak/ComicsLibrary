package com.example.comicslibrary.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CharacterEntity::class], version = 2, exportSchema = false)
abstract class ComicsDatabase : RoomDatabase() {

    abstract fun characterDao() : CharacterDao

}