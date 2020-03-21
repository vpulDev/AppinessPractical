package com.app.task.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.app.task.R
import kotlinx.android.synthetic.main.activity_enterprise_list.view.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
class EnterpriseListActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<EnterpriseListActivity>
            = ActivityTestRule(EnterpriseListActivity::class.java)

    fun testClickAtPosition(){
        Espresso.onView(withId(R.id.rv_enterprise_list)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            click()));

    }
}