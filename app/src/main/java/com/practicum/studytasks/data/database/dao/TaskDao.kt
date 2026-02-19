package com.practicum.studytasks.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.practicum.studytasks.data.database.entity.TaskEntity

@Dao
interface TaskDao {

    @Insert
    suspend fun insertTask(task: TaskEntity)
//
//    @Transaction
//    @Query("SELECT * FROM playlists WHERE id = :playlistId")
//    fun getPlaylistWithTracks(playlistId: Long): Flow<PlaylistWithTracks?>
//
//    @Transaction
//    @Query("SELECT * FROM playlists WHERE isSystem = 0")
//    fun getAllPlaylistsWithTracks(): Flow<List<PlaylistWithTracks>>
//
//    @Query("SELECT * FROM playlists WHERE isSystem = 1 LIMIT 1")
//    suspend fun getFavoritePlaylistOnce(): PlaylistEntity?
//
//    @Transaction
//    @Query("SELECT * FROM playlists WHERE isSystem = 1 LIMIT 1")
//    fun getFavoritePlaylist(): Flow<PlaylistWithTracks?>
//
//    @Update
//    suspend fun updatePlaylist(playlist: PlaylistEntity)
//
//    @Query("DELETE FROM playlists WHERE id = :id")
//    suspend fun deletePlaylistById(id: Long)
}
