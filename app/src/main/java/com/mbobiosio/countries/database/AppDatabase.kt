package com.mbobiosio.countries.database

import android.content.Context
import androidx.room.*
import com.mbobiosio.countries.model.dbmodel.CountryDbModel
import com.mbobiosio.countries.util.Converter

@Database(
    entities = [CountryDbModel::class],
    version = 1,
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
                    ).build()
                }
                INSTANCE = instance
                return instance
            }
        }

    }
}
