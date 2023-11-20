package com.example.cs124h_dining_app.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuRequest(
    @SerialName("DiningOptionID")
    val id: String, // LAR VEG: 12, IKE: 1, ISR: 3, LAR KOSHER: 23, LAR: 5, PAR: 2
    @SerialName("mealDate")
    val date: String, //YY-MM-DD
)

@Serializable
data class SourceMenuItem(
    @SerialName("EventDate")
    val date: String,
    @SerialName("DiningMenuID")
    val diningMenuID: Int,
    @SerialName("ServingUnit")
    val cafe: String,
    @SerialName("Course")
    val course: String,
    @SerialName("CourseSort")
    val courseSort: Int,
    @SerialName("FormalName")
    val name: String,
    @SerialName("Meal")
    val meal: String,
    @SerialName("Traits")
    val traits: String?,
    @SerialName("DiningOptionID")
    val diningOptionID: Int,
    @SerialName("ScheduleID")
    val scheduleID: Int,
    @SerialName("ItemID")
    val itemID: Int,
    @SerialName("Category")
    val category: String,
    @SerialName("EventDateGMT")
    val dateGMT: Int,
)

data class MenuItem(
    val name: String,
    val course: String,
    val traits: String, // note that traits could be a blank string
)