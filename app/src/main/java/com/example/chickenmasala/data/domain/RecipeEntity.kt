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
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()?:"",
        parcel.createStringArrayList()?.toList()?: emptyList(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()?:"",
        parcel.createStringArrayList()?.toList()?: emptyList(),
        parcel.readString()?:"",
        parcel.createStringArrayList()?.toList()?: emptyList(),
        parcel.readString()?:"",
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.createStringArrayList()?.toList()?: emptyList()
    )
    companion object CREATOR : Parcelable.Creator<RecipeEntity> {
        override fun createFromParcel(parcel: Parcel): RecipeEntity {
            return RecipeEntity(parcel)
        }

        override fun newArray(size: Int): Array<RecipeEntity?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int { return 0}

    override fun writeToParcel(dest: Parcel, flags: Int) {}
}
