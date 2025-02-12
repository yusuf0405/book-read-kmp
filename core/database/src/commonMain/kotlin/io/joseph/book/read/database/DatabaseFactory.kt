package io.joseph.book.read.database

import androidx.room.RoomDatabase
import io.joseph.book.read.common.PlatformConfiguration

expect class DatabaseFactory constructor(platformConfiguration: PlatformConfiguration) {
    fun create(): RoomDatabase.Builder<FavoriteBookDatabase>
}