package com.example.comicslibrary

sealed class Destination(val route : String) {
    object Library : Destination("library")
    object Collection : Destination("collection")
    object CharacterDetail : Destination("character/{characterId}") {
        fun createRoute(characterId : Int?) = "character/$"
    }
}