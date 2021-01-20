package com.mbobiosio.countries.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mbobiosio.countries.model.dbmodel.CountryDbModel
import com.mbobiosio.countries.util.Converter

@Database(
    entities = [CountryDbModel::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(value = [Converter::class])
abstract class AppDatabase : RoomDatabase() {

    abstract val countryDao: CountryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(AppDatabase::class.java) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                    )   //Destroy the first version of the database as the data contained is no longer required.
                        //The first version of the database contained a column for Room auto-generated primary key of type Long,
                        //which has been removed from the second version of the database
                        .fallbackToDestructiveMigrationFrom(1)
                        .build()
                }
                INSTANCE = instance
                return instance
            }
        }

    }
}
