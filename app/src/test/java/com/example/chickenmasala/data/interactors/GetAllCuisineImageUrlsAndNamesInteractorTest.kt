package com.example.chickenmasala.data.interactors

import com.example.chickenmasala.data.domain.RecipeEntity
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable

class GetAllCuisineImageUrlsAndNamesInteractorTest{

     private lateinit var cuisineList: GetAllCuisineImageUrlsAndNamesInteractor


     @MockK
     private lateinit var dataSource: FoodDataSource<RecipeEntity>

     @MockK
     private lateinit var indianCuisine: RecipeEntity

     @MockK
     private lateinit var usaCuisine: RecipeEntity


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
             usaCuisine.cuisine
         } returns "usa"

         every {
             indianCuisine.imageUrl
         } returns "img2.url"

         every {
             dataSource.getAllItems()
         }returns listOf(indianCuisine,usaCuisine)
         cuisineList= GetAllCuisineImageUrlsAndNamesInteractor(dataSource)



     }

    @Test
    fun should_ThrowException_When_NameCuisineNotFound(){
        // given
            val cuisine = ""
            val imageUrl = "image.com"

        // when
            val result = Executable { cuisineList.execute(cuisine, imageUrl)}

        // then
            assertThrows(Exception::class.java,result)
    }

    @Test
    fun should_ThrowException_When_ImageUrlCuisineNotFound(){
        // given
            val cuisine = "indian"
            val imageUrl = ""

        // when
            val result = Executable { cuisineList.execute(cuisine, imageUrl)}

        // then
            assertThrows(Exception::class.java,result)
    }

    @Test
    fun should_ReturnAllCuisine_When_ImageUrlAndNameCuisineFound(){
        // given
            val cuisine = "indian"
            val imageUrl = "img.url"

            val listCuisine = listOf(indianCuisine,usaCuisine)
        // when
            val result =  cuisineList.execute(cuisine, imageUrl)

        // then
            assertEquals(listCuisine,result)
    }


 }