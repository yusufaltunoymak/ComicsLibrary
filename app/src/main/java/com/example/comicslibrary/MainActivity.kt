package com.example.comicslibrary

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.comicslibrary.ui.theme.ComicsLibraryTheme
import com.example.comicslibrary.view.CharacterDetailScreen
import com.example.comicslibrary.view.CharactersBottomNav
import com.example.comicslibrary.view.CollectionScreen
import com.example.comicslibrary.view.LibraryScreen
import com.example.comicslibrary.viewmodel.CollectionViewModel
import com.example.comicslibrary.viewmodel.LibraryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val vm by viewModels<LibraryViewModel>()
    private val cvm by viewModels<CollectionViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComicsLibraryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    ScaffoldWithBottomMenu(navController = navController,vm,cvm)

                    }

                }
            }

        }
    }


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScaffoldWithBottomMenu(navController : NavHostController,vm : LibraryViewModel, cvm : CollectionViewModel) {
    Scaffold(bottomBar = { CharactersBottomNav(navController = navController) }
    ) {paddingValues ->
        NavHost(navController = navController, startDestination = Destination.Library.route) {

            composable(route = Destination.Library.route) {
                LibraryScreen(navController,vm)
            }

            composable(route = Destination.CharacterDetail.route) {
                val id = it.arguments?.getString("characterId")?.toIntOrNull()
                if(id != null) {
                    vm.retrieveSingleCharacter(id)
                    CharacterDetailScreen(
                        lvm = vm,
                        cvm = cvm,
                        paddingValues = paddingValues,
                        navController = navController
                    )
                }
            }

            composable(route = Destination.Collection.route) {
                CollectionScreen()
            }

        }
    }
}