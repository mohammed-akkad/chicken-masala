package com.example.chickenmasala.data.interactors

import com.example.chickenmasala.data.domain.RecipeEntity
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable

class GetListRecipesRelatedToCertainRecipeInteractorTest {
    private lateinit var recipeRelated: GetListRecipesRelatedToCertainRecipeInteractor

    @MockK
    private lateinit var dataSource: FoodDataSource<RecipeEntity>

    @MockK
    private lateinit var recipeIndianOne: RecipeEntity

    @MockK
    private lateinit var recipeIndianTwo: RecipeEntity

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)

        every {
            recipeIndianOne.cuisine
        } returns "indian"

        every {
            recipeIndianTwo.cuisine
        } returns "indian"

        every {
            recipeIndianOne.ingredients
        } returns listOf("chickens", "onion")

        every {
            recipeIndianTwo.ingredients
        } returns listOf("tomato", "potato")

        every {
            dataSource.getAllItems()
        } returns listOf(recipeIndianOne,recipeIndianTwo)
        recipeRelated = GetListRecipesRelatedToCertainRecipeInteractor(dataSource)

    }


    @Test
    fun should_ReturnThrowException_When_LimitIsZero() {
        // given
        val limit = 0
        val ingredients = listOf("chickens", "onion")
        val cuisine = "indian"


        // when
       val relatedRecipe =  Executable {recipeRelated.execute(limit = limit, ingredients = ingredients, cuisine = cuisine)}

        // then
        assertThrows(Exception::class.java,relatedRecipe)
    }

    @Test
    fun should_ReturnNull_When_LimitIsNegative() {
        // given
        val limit = -5
        val ingredients = listOf("chickens", "onion")
        val cuisine = "indian"


        // when
        val relatedRecipe =  Executable {recipeRelated.execute(limit = limit, ingredients = ingredients, cuisine = cuisine)}

        // then
        assertThrows(Exception::class.java,relatedRecipe)
    }

    @Test
    fun should_ReturnRecipeRelated_When_LimitIsPositive() {
        // given
        val limit = 1
        val ingredients = listOf("chickens", "onion")
        val cuisine = "indian"
        val relatedRecipeExpected = listOf(recipeIndianOne)


        // when
       val relatedRecipeActual =  recipeRelated.execute(limit = limit, ingredients = ingredients, cuisine = cuisine)

        // then
        assertEquals(relatedRecipeExpected,relatedRecipeActual)
    }

    @Test
    fun should_ReturnEmptyList_When_CuisineNotFound() {
        // given
        val limit = 1
        val ingredients = listOf("watermelon")
        val cuisine = "thai"

        // when
       val relatedRecipe =  recipeRelated.execute(limit = limit, ingredients = ingredients, cuisine = cuisine)

        // then
        assertTrue(relatedRecipe.isEmpty())
    }
    @Test
    fun should_ReturnEmptyList_When_IngredientsNotFound() {
        // given
        val limit = 1
        val ingredients = listOf("lentil")
        val cuisine = "palestine"

        // when
       val relatedRecipe =  recipeRelated.execute(limit = limit, ingredients = ingredients, cuisine = cuisine)

        // then
        assertTrue(relatedRecipe.isEmpty())
    }


}