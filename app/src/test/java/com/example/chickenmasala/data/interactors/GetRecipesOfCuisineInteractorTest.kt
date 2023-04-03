package com.example.chickenmasala.data.interactors
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.interactors.FoodDataSource
import com.example.chickenmasala.interactors.GetRecipesOfCuisineInteractor
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable

class GetRecipesOfCuisineInteractorTest {

    private lateinit var interactor: GetRecipesOfCuisineInteractor

    @MockK
    private lateinit var dataSource: FoodDataSource<RecipeEntity>

    @MockK
    private lateinit var indianRecipe1: RecipeEntity

    @MockK
    private lateinit var indianRecipe2: RecipeEntity

    @MockK
    private lateinit var iraqiRecipe: RecipeEntity

    @MockK
    private lateinit var palestinianRecipe: RecipeEntity


    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)

        every { indianRecipe1.cuisine } returns "indian"
        every { indianRecipe2.cuisine } returns "indian"
        every { iraqiRecipe.cuisine } returns "iraqi"
        every { palestinianRecipe.cuisine } returns "palestinian"

        every {
            dataSource.getAllItems()
        } returns listOf(indianRecipe1, indianRecipe2, iraqiRecipe, palestinianRecipe)
        interactor = GetRecipesOfCuisineInteractor(dataSource)

    }


    @Test
    fun should_ReturnWholeListFilteredByCuisine_When_LimitIsZero() {
        // Given
        val cuisine = "indian"
        val limit = 0
        val expectedRecipes = setOf(indianRecipe1, indianRecipe2)

        // When
        val result = interactor.execute(cuisine, limit)

        // Then
        assertEquals(expectedRecipes, result.toSet())
    }

    @Test
    fun should_ReturnEmptyList_When_CuisineNotFound() {
        // Given
        val cuisine = "Mexican"
        val limit = 5

        // When
        val result = interactor.execute(cuisine, limit)

        // Then
        assertTrue(result.isEmpty())
    }


    @Test
    fun should_ThrowException_When_TheLimitIsNegative() {
        // Given
        val cuisine = "Mexican"
        val limit = -5

        // When
        val result = Executable{ interactor.execute(cuisine, limit)}

        // Then
        assertThrows(Exception::class.java, result)
    }


}