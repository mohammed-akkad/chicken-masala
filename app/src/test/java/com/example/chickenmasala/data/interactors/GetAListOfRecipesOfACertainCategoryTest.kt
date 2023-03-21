package com.example.chickenmasala.data.interactors

import androidx.lifecycle.viewmodel.CreationExtras
import com.example.chickenmasala.data.domain.RecipeEntity
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class GetAListOfRecipesOfACertainCategoryTest{

     private lateinit var interactor: GetAListOfRecipesOfACertainCategoryInteractor

     @MockK
     private lateinit var dataSource: FoodDataSource<RecipeEntity>

     @MockK
     private lateinit var indianRecipe: RecipeEntity

    @MockK
    private lateinit var indianRecipe2: RecipeEntity
    @MockK
    private lateinit var indianRecipe3: RecipeEntity


    @BeforeEach
     fun setup() {
         MockKAnnotations.init(this)


         every { indianRecipe.tags } returns listOf("Chicken")

        every { indianRecipe2.tags } returns listOf("Chicken")

        every { indianRecipe3.tags } returns listOf("Fish")




        every {
             dataSource.getAllItems()
         } returns listOf(indianRecipe,indianRecipe2 , indianRecipe3)
         interactor = GetAListOfRecipesOfACertainCategoryInteractor(dataSource)

     }

     @Test
     fun shouldReturnAllRecipes_WhenCategoryIsContainsChicken(){
         // when Category Is Contains Chicken
         val recipes = interactor.execute("Chicken")
         // then

         Assertions.assertEquals(listOf(indianRecipe,indianRecipe2), recipes)
     }

    @Test
    fun shouldReturnAllRecipes_WhenCategoryIsContainsFish(){
        // when Category Is Contains Fish
        val recipes = interactor.execute("fish")
        // then

        Assertions.assertEquals(listOf(indianRecipe3,), recipes)
    }

    @Test
    fun shouldReturnNull_WhenCategoryIsNull(){
        // when Category Is Contains Fish
        val recipes = interactor.execute(null)
        // then
        assertNull( recipes)
    }
 }