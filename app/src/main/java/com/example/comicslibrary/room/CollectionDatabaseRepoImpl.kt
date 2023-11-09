package com.example.comicslibrary.room

import kotlinx.coroutines.flow.Flow

class CollectionDatabaseRepoImpl(private val characterDao: CharacterDao) : CollectionDatabaseRepo {

    override suspend fun getCharactersFromRepo(): Flow<List<CharacterEntity>> = characterDao.getCharacters()

    override suspend fun getCharacterFromRepo(characterId: Int): Flow<CharacterEntity> = characterDao.getCharacter(characterId)

    override suspend fun addCharacterToRepo(character: CharacterEntity) = characterDao.addCharacter(character)

    override suspend fun updateCharacterInRepo(character: CharacterEntity) = characterDao.updateCharacter(character)

    override suspend fun deleteCharacterFromRepo(character: CharacterEntity) = characterDao.deleteCharacter(character)
}