package co.com.ceiba.mobile.pruebadeingreso.view


import android.support.test.espresso.ViewInteraction
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.test.suitebuilder.annotation.LargeTest
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.ui.main.MainActivity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.endsWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class Calificador {

    @Rule
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return (parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position))
            }
        }
    }

    @Test
    fun emptyTest() {

        clickInputSearch()

        keypressInputSearch("empty")

        onView(allOf(withText("List is empty"))).check(matches(withText("List is empty")))


    }

    @Test
    fun ervinTest() {

        clickInputSearch()

        keypressInputSearch("Ervin")

        onView(allOf(withId(R.id.name))).check(matches(withText("Ervin Howell")))
        onView(allOf(withId(R.id.phone))).check(matches(withText("010-692-6593 x09125")))
        onView(allOf(withId(R.id.email))).check(matches(withText("Shanna@melissa.tv")))


    }

    @Test
    fun leanneTest() {

        clickInputSearch()

        keypressInputSearch("Leanne")

        onView(allOf(withId(R.id.name))).check(matches(withText("Leanne Graham")))
        onView(allOf(withId(R.id.phone))).check(matches(withText("1-770-736-8031 x56442")))
        onView(allOf(withId(R.id.email))).check(matches(withText("Sincere@april.biz")))

    }

    @Test
    @Throws(InterruptedException::class)
    fun leannePostClickVerPublicacionesTest() {

        clickInputSearch()
        keypressInputSearch("Leanne")

        onView(allOf(withId(R.id.btn_view_post))).perform(click())
        Thread.sleep(4000)

        onView(allOf(withId(R.id.name))).check(matches(withText(endsWith("Leanne Graham"))))
        onView(allOf(withId(R.id.phone))).check(matches(withText("1-770-736-8031 x56442")))


        onView(allOf(withId(R.id.title), withText("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"))).check(matches(withText("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")))


    }

    private fun keypressInputSearch(valueToWrite: String) {
        val appCompatEditText2 = onView(
                allOf(withId(R.id.editTextSearch),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayoutSearch),
                                        0),
                                0),
                        isDisplayed()))
        appCompatEditText2.perform(replaceText(valueToWrite), closeSoftKeyboard())
    }

    private fun clickInputSearch() {
        val appCompatEditText = onView(
                allOf(withId(R.id.editTextSearch),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayoutSearch),
                                        0),
                                0),
                        isDisplayed()))
        appCompatEditText.perform(click())
    }

}
