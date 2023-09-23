package com.example.kotlin

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class PokemonActivityTest {

    @JvmField
    @Rule
    val activityScenarioRule: ActivityScenarioRule<PokemonActivity> = ActivityScenarioRule(PokemonActivity::class.java)

    @Test
    fun useAppContext() {
        onView(withId(R.id.pokemon_list))
            .perform(click())

    }
}