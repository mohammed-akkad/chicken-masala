package com.example.chickenmasala.data.interactors

import com.example.chickenmasala.data.domain.RecipeEntity
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class GetAListOfRandomRecipesTest{
     private lateinit var interactor: GetAListOfRandomRecipesInteractor

     @MockK
     private lateinit var dataSource: FoodDataSource<RecipeEntity>

     @MockK
     private lateinit var indianRecipe: RecipeEntity
    @MockK
    private lateinit var indianRecipe2: RecipeEntity

     @BeforeEach
     fun setup() {
         MockKAnnotations.init(this)

         every { indianRecipe.name }returns "Pizza"
         every { indianRecipe2.name }returns "Mojadara"



         every {
             dataSource.getAllItems()
         } returns listOf(indianRecipe,indianRecipe2)
         interactor = GetAListOfRandomRecipesInteractor(dataSource)

     }

     @Test
     fun shouldReturnAllRecipes_WhenTheLimitIsGraterThanNumberOgRecipes (){
         // when the limit =100 and the recipes number = 2
         val recipes = interactor.execute(100)
         // then
         assertEquals(listOf(indianRecipe , indianRecipe2) , recipes)
     }

    @Test
    fun shouldReturnOneRecipe_WhenTheLimitIsOne(){
        // when the limit =1
        val recipes = interactor.execute(1)
        // then
        assertEquals(listOf( indianRecipe) .size==1 , recipes.size==1)
    }
 }


