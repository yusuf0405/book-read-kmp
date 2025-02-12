package io.joseph.book.read.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import io.joseph.book.read.database.models.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteBookDao {

    @Upsert
    suspend fun upsert(book: BookEntity)

    @Query("SELECT * FROM BookEntity")
    fun getFavoriteBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM BookEntity WHERE id = :id")
    suspend fun getFavoriteBook(id: String): BookEntity?

    @Query("DELETE FROM BookEntity WHERE id = :id")
    suspend fun deleteFavoriteBook(id: String)
}