package io.joseph.book.read.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.joseph.book.read.database.FavoriteBookDatabase.Companion.DB_VERSION
import io.joseph.book.read.database.convertor.BookDatabaseConstructor
import io.joseph.book.read.database.convertor.StringListTypeConverter
import io.joseph.book.read.database.dao.FavoriteBookDao
import io.joseph.book.read.database.models.BookEntity

@Database(
    entities = [BookEntity::class],
    version = DB_VERSION
)
@TypeConverters(
    StringListTypeConverter::class
)
@ConstructedBy(BookDatabaseConstructor::class)
abstract class FavoriteBookDatabase: RoomDatabase() {
    abstract val favoriteBookDao: FavoriteBookDao

    companion object {
        const val DB_NAME = "book.db"
        const val DB_VERSION = 1
    }
}