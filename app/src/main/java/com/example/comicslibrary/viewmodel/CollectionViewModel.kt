package com.example.comicslibrary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comicslibrary.model.CharacterResult
import com.example.comicslibrary.room.CharacterEntity
import com.example.comicslibrary.room.CollectionDatabaseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(private val repo:CollectionDatabaseRepo) : ViewModel() {
    val currentCharacter = MutableStateFlow<CharacterEntity?>(null)
    val collection = MutableStateFlow<List<CharacterEntity>>(listOf())

    init {
        getCollection()
    }

    private fun getCollection() {
        viewModelScope.launch {
            repo.getCharactersFromRepo().collect {
                collection.value = it
            }
        }
    }

    fun setCurrentCharacterId(characterId : Int?) {
        characterId?.let {
            viewModelScope.launch {
                repo.getCharacterFromRepo(it).collect {
                    currentCharacter.value = it
                }
            }
        }
    }

    fun addCharacter(character : CharacterResult) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addCharacterToRepo(CharacterEntity.fromCharacter(character))
        }
    }

    fun deleteCharacter(character : CharacterEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteCharacterFromRepo(character)
        }
    }
}