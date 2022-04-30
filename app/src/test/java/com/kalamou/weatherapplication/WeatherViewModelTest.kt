package com.kalamou.weatherapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import com.kalamou.weatherapplication.api.NetworkServices
import com.kalamou.weatherapplication.db.AppDatabase
import com.kalamou.weatherapplication.db.DataDao
import com.kalamou.weatherapplication.db.ItemNameDao
import com.kalamou.weatherapplication.db.model.ItemName
import com.kalamou.weatherapplication.model.Data
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.Flow
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@Config(sdk = [28], application = WeatherApplicationTestApp::class)
@RunWith(RobolectricTestRunner::class)
class WeatherViewModelTest {

    private lateinit var vm: WeatherViewModel

    @MockK
    private lateinit var networkServices: NetworkServices

    @MockK
    private lateinit var itemDao: ItemNameDao

    @MockK
    private lateinit var dataDao: DataDao

    @Before
    fun initialStat() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        vm = WeatherViewModel(
            networkServices = networkServices,
            itemNameDao = itemDao,
            dataDao = dataDao
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }


    @Test
    fun getItemNames() {
        // Given
        val dao = mockk<ItemNameDao>()

        val list : LiveData<List<ItemName>> = listOf(
            ItemName(0, "test1"),
            ItemName(1, "test2")
        ) as LiveData<List<ItemName>>

        val item = ItemName(0, "test1")
        // When
        coEvery { dao.getItems() } returns list.asFlow()
        coEvery { dao.getItem(0) } returns item

        // Then
        assertEquals(item,  ItemName(0, "test1"))
    }

    @Test
    fun getDataList() {
        // Given
        val dao = mockk<DataDao>()

        // When
        every { vm.dataList } returns dataDao.getData().asLiveData()

        // Then
    }

    @Test
    fun getData() {
        // Given

        // When

        // Then
    }

    @Test
    fun getListData() {
        // Given

        // When

        // Then
    }

    @Test
    fun testGetData() {
        // Given

        // When

        // Then
    }

    @Test
    fun addItemNewItem() {
        // Given

        // When

        // Then
    }

    @Test
    fun getNewEntry() {
        // Given

        // When

        // Then
    }

    @Test
    fun insertNewData() {
        // Given

        // When

        // Then
    }

    @Test
    fun getDataToDisplay() {
        // Given

        // When

        // Then
    }
}