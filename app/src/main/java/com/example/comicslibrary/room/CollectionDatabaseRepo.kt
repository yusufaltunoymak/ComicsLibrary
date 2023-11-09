package com.example.comicslibrary.room

import kotlinx.coroutines.flow.Flow

interface CollectionDatabaseRepo {
    suspend fun getCharactersFromRepo() : Flow<List<CharacterEntity>>

    suspend fun getCharacterFromRepo(characterId : Int) : Flow<CharacterEntity>

    suspend fun addCharacterToRepo(character : CharacterEntity)

    suspend fun updateCharacterInRepo(character : CharacterEntity)

    suspend fun deleteCharacterFromRepo(character: CharacterEntity)
}