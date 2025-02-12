package io.joseph.book.read.database

import androidx.room.Room
import androidx.room.RoomDatabase
import io.joseph.book.read.common.PlatformConfiguration

actual class DatabaseFactory actual constructor(
    private val platformConfiguration: PlatformConfiguration
) {

    actual fun create(): RoomDatabase.Builder<FavoriteBookDatabase> {
        val appContext = platformConfiguration.androidContext
        val dbFile = appContext.getDatabasePath(FavoriteBookDatabase.DB_NAME)
        return Room.databaseBuilder(
            context = appContext,
            name = dbFile.absolutePath
        )
    }
}