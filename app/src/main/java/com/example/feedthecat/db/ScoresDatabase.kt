package com.example.feedthecat.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Score::class],
    version = 1,
    exportSchema = false
)
abstract class ScoresDatabase : RoomDatabase() {

    abstract val scoresDao: ScoresDAO

    companion object {
        @Volatile
        private var INSTANCE: ScoresDatabase? = null

        fun getInstance(context: Context): ScoresDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ScoresDatabase::class.java,
                        "scores_database"
                    )
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}