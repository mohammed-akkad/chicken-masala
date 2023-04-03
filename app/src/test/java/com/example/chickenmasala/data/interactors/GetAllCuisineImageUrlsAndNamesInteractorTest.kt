package com.example.chickenmasala.data.interactors

import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.interactors.FoodDataSource
import com.example.chickenmasala.interactors.GetAllCuisineImageUrlsAndNamesInteractor
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable

class GetAllCuisineImageUrlsAndNamesInteractorTest {

    private lateinit var cuisineList: GetAllCuisineImageUrlsAndNamesInteractor


    @MockK
    private lateinit var dataSource: FoodDataSource<RecipeEntity>

    @MockK
    private lateinit var indianCuisine: RecipeEntity




    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)

        every {
            indianCuisine.cuisine
        } returns "indian"

        every {
            indianCuisine.imageUrl
        } returns "img.url"


        every {
            dataSource.getAllItems()
        } returns listOf(indianCuisine)
        cuisineList = GetAllCuisineImageUrlsAndNamesInteractor(dataSource)

    }

    @Test
    fun should_ReturnAllCuisine_When_ExecutFunction() {
        // given
            val recipesList = listOf(indianCuisine)

        //when
            val result = cuisineList.execute()

        // then
            assertEquals(recipesList,result)

    }


}