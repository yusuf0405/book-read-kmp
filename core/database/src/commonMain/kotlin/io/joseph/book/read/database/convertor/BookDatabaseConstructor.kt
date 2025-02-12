package io.joseph.book.read.database.convertor

import androidx.room.RoomDatabaseConstructor
import io.joseph.book.read.database.FavoriteBookDatabase

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object BookDatabaseConstructor: RoomDatabaseConstructor<FavoriteBookDatabase> {
    override fun initialize(): FavoriteBookDatabase
}