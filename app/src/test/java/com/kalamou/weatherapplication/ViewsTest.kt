package com.kalamou.weatherapplication

import android.view.View
import androidx.test.core.app.ApplicationProvider
import com.kalamou.weatherapplication.utils.enableOrDisable
import com.kalamou.weatherapplication.utils.visibleOrGone
import com.kalamou.weatherapplication.utils.visibleOrInvisible
import io.mockk.unmockkAll
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [28], application = WeatherApplicationTestApp::class)
@RunWith(RobolectricTestRunner::class)
class ViewsTest {

    @Before
    fun initialState() {
        // Initialized the mocks
        unmockkAll()
    }

    @Test
    fun visibleOrGone_true() {
        // Given
        val v = View(ApplicationProvider.getApplicationContext())

        // When
        v.visibleOrGone(true)

        // Then
        Assert.assertEquals(View.VISIBLE, v.visibility)
    }

    @Test
    fun visibleOrGone_false() {
        // Given
        val v = View(ApplicationProvider.getApplicationContext())

        // When
        v.visibleOrGone(false)

        // Then
        Assert.assertEquals(View.GONE, v.visibility)
    }

    @Test
    fun visibleOrInvisible_true() {
        // Given
        val v = View(ApplicationProvider.getApplicationContext())

        // When
        v.visibleOrInvisible(true)

        // Then
        Assert.assertEquals(View.VISIBLE, v.visibility)
    }

    @Test
    fun visibleOrInvisible_false() {
        // Given
        val v = View(ApplicationProvider.getApplicationContext())

        // When
        v.visibleOrInvisible(false)

        // Then
        Assert.assertEquals(View.INVISIBLE, v.visibility)
    }

    @Test
    fun enableOrDisable_true() {
        // Given
        val v = View(ApplicationProvider.getApplicationContext())

        // When
        v.enableOrDisable(true)

        // Then
        Assert.assertEquals(View.VISIBLE, v.visibility)
    }

    @Test
    fun enableOrDisable_false() {
        // Given
        val v = View(ApplicationProvider.getApplicationContext())

        // When
        v.enableOrDisable(false)

        // Then
        Assert.assertEquals(View.VISIBLE, v.visibility)
    }
}