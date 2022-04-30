package com.kalamou.weatherapplication

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.*
import com.kalamou.weatherapplication.api.NetworkServices
import com.kalamou.weatherapplication.db.DataDao
import com.kalamou.weatherapplication.db.ItemNameDao
import com.kalamou.weatherapplication.db.model.ItemName
import com.kalamou.weatherapplication.model.Data
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val networkServices: NetworkServices,
    private val itemNameDao: ItemNameDao,
    private val dataDao: DataDao
) : ViewModel() {

    val itemNames: LiveData<List<ItemName>> = itemNameDao.getItems().asLiveData()
    val dataList: LiveData<List<Data>> = dataDao.getData().asLiveData()

    private var _data = MutableLiveData<Data?>()

    val data: LiveData<Data?>
        get() = _data

    private var _listData = MutableLiveData<List<Data>>()
    val listData: LiveData<List<Data>>
        get() = _listData

    fun getData(id: Int) {
        viewModelScope.launch {
            dataDao.getData(id)
        }
    }


    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun addItemNewItem(itemName: String) {
        val newItem = getNewEntry(itemName)
        insertItem(newItem)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getNewEntry(name: String): ItemName {
        return ItemName(name = name)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun insertItem(itemName: ItemName) {
        viewModelScope.launch {
            itemNameDao.insert(itemName)
        }
    }

    fun insertNewData(data: Data) {
        insertData(data)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun insertData(data: Data) {
        viewModelScope.launch {
            dataDao.insert(data)
        }
    }

    fun getDataToDisplay(name: String): Data? {
        viewModelScope.launch {
            val data = networkServices.proceedGetDataByCity(name).body()
            _data.value = data
        }
        return _data.value
    }
}

class WeatherViewModelFactory(
    private val networkServices: NetworkServices,
    private val itemNameDao: ItemNameDao,
    private val dataDao: DataDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(networkServices, itemNameDao, dataDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
