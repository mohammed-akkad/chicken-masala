package com.example.chickenmasala.data.interactors
import com.example.chickenmasala.data.domain.RecipeEntity
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable

class GetAListOfRecipesBasedOnRecipeNameWithFiltersAsArgumentsTest {

    private lateinit var interactor: GetAListOfRecipesBasedOnRecipeNameWithFiltersAsArgumentsInteractor

    @MockK
    private lateinit var dataSource: FoodDataSource<RecipeEntity>

    @MockK
    private lateinit var indianRecipe1: RecipeEntity

    @MockK
    private lateinit var indianRecipe2: RecipeEntity

    @MockK
    private lateinit var palestinianRecipe: RecipeEntity


    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)

        every { indianRecipe1.name }returns "Pizza"
        every { indianRecipe1.cuisine } returns "indian"
        every {  indianRecipe1.totalTime} returns 10
        every { indianRecipe1.ingredientsCount }returns 5
        every { indianRecipe1.tags }returns listOf("Chicken")


        every { indianRecipe2.name }returns "Salad"
        every { indianRecipe2.cuisine } returns "indian"
        every { indianRecipe2.totalTime }returns 20
        every { indianRecipe2.ingredientsCount }returns 10
        every { indianRecipe2.tags }returns listOf("Salad")


        every { palestinianRecipe.name }returns "mojadara"
        every { palestinianRecipe.cuisine } returns "palestinian"
        every { palestinianRecipe.totalTime }returns 15
        every { palestinianRecipe.ingredientsCount }returns 7


        every {
            dataSource.getAllItems()
        } returns listOf(indianRecipe1, indianRecipe2, palestinianRecipe)
        interactor = GetAListOfRecipesBasedOnRecipeNameWithFiltersAsArgumentsInteractor(dataSource)

    }

    @Test
    fun shouldReturnAllRecipesWithNonFilters(){
        // when
        val recipes = interactor.execute()
        // then

        assertEquals(listOf(indianRecipe1 , indianRecipe2 , palestinianRecipe) , recipes)
        assertEquals(listOf(indianRecipe1 , indianRecipe2 , palestinianRecipe) , recipes)
    }

    @Test
    fun shouldReturnRecipes_WhenFilterTotalTimeEqualOrUnderThan10Mins(){

        // when the total time equal or under than 10 mins
        val recipes = interactor.execute(totalTimeMins = 10)

        // then
        assertEquals(listOf(indianRecipe1 ) , recipes)
    }

    @Test
    fun shouldReturnRecipes_WhenFilterTotalTimeEqualOrUnderThan20Mins_AndFilterIngredientsCountEqualOrUnderThan10(){

        // when the total time equal or under than 20 mins and ingredientsCount equal or under than 10
        val recipes = interactor.execute(totalTimeMins = 20 , ingredientsCount = 10)

        // then
        assertEquals(listOf(indianRecipe1, indianRecipe2 , palestinianRecipe ) , recipes)
    }


    @Test
    fun shouldReturnRecipesWithFiltersCategoryAndName(){

        // when category is equal chicken and name is pizza
        val recipes = interactor.execute(category = "Chicken" , recipeName = "pizza")
        // then
        assertEquals(listOf(indianRecipe1 ) , recipes)

    }

    @Test
    fun shouldReturnRecipesWithFiltersIsTotalTimAndNameAndCuisine(){
        //when name is pizza , total time equal or under than 10 and cuisine is india
        val recipes = interactor.execute( cuisine = "indian" , recipeName = "pizza" , totalTimeMins = 10)

        // then
        assertEquals(listOf(indianRecipe1 , ) , recipes)
    }



}