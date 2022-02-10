package com.example.bajp_submission3.utils

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResources {
    private const val RESOURCE = "GLOBAL"
    val idlingResource: CountingIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() = idlingResource.increment()
    fun decrement() = idlingResource.decrement()
}