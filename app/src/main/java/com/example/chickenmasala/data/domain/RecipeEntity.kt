package com.example.chickenmasala.data.domain

import android.os.Parcel
import android.os.Parcelable

data class RecipeEntity(
    val name: String,
    val ingredients: List<String>,
    val totalTime: Int?,
    val cuisine: String,
    val instructions: List<String>,
    val url: String,
    val cleanedIngredients: List<String>,
    val imageUrl: String,
    val ingredientsCount: Int?,
    val tags:List<String>
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.createStringArrayList()!!,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()!!,
        parcel.createStringArrayList()!!,
        parcel.readString()!!,
        parcel.createStringArrayList()!!,
        parcel.readString()!!,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.createStringArrayList()!!
    )
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeStringList(ingredients)
        parcel.writeValue(totalTime)
        parcel.writeString(cuisine)
        parcel.writeStringList(instructions)
        parcel.writeString(url)
        parcel.writeStringList(cleanedIngredients)
        parcel.writeString(imageUrl)
        parcel.writeValue(ingredientsCount)
        parcel.writeStringList(tags)
    }
    companion object CREATOR : Parcelable.Creator<RecipeEntity> {
        override fun createFromParcel(parcel: Parcel): RecipeEntity {
            return RecipeEntity(parcel)
        }

        override fun newArray(size: Int): Array<RecipeEntity?> {
            return arrayOfNulls(size)
        }
    }
}
