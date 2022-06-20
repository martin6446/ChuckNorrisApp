package com.example.chucknorrisjokesapp.ui.categories

sealed class CategoriesEvent(){
    data class OnCategoryClick(val category: String): CategoriesEvent()
}
