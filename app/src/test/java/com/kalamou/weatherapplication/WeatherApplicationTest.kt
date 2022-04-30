package com.kalamou.weatherapplication

import androidx.test.core.app.ApplicationProvider
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@Config(sdk = [28], application = WeatherApplicationTestApp::class)
@RunWith(RobolectricTestRunner::class)
class WeatherApplicationTest {
    @Test
    fun getAppVersion_default() {
        // Given
        val app: WeatherApplicationTestApp = ApplicationProvider.getApplicationContext()

        // When
        val v = app.getVersionString()

        // Then
        Assert.assertTrue(v.contains(BuildConfig.VERSION_NAME))
    }

}