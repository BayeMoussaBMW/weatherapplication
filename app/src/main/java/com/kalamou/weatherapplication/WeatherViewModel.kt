package com.kalamou.weatherapplication

import androidx.lifecycle.*
import com.kalamou.weatherapplication.api.NetworkServices
import com.kalamou.weatherapplication.db.ItemNameDao
import com.kalamou.weatherapplication.db.model.ItemName
import com.kalamou.weatherapplication.model.Data
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val networkServices: NetworkServices,
    private val itemNameDao: ItemNameDao
) : ViewModel() {

    val itemNames: LiveData<List<ItemName>> = itemNameDao.getItems().asLiveData()

    private var _data = MutableLiveData<Data?>()

    val data: LiveData<Data?>
        get() = _data

    private var _currentItemId = MutableLiveData<Int?>()

    val currentItemId: LiveData<Int?>
        get() = _currentItemId

    private var _currentItemName = MutableLiveData<Data?> ()

    val currentItemName: LiveData<Data?>
        get() = _currentItemName

    fun getCurrentItem(id: Int): ItemName{
        getItem(id)
        return ItemName(id = id, currentItemName.value!!.name)
    }

    fun getItem(id: Int){
        viewModelScope.launch {
            itemNameDao.getItem(id)
        }
    }

    fun addItemNewItem(itemName: String) {
        val newItem = getNewEntry(itemName)
        insertItem(newItem)
    }

    private fun getNewEntry(name: String): ItemName {
        return ItemName(name = name)
    }

    private fun insertItem(itemName: ItemName) {
        viewModelScope.launch {
            itemNameDao.insert(itemName)
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
    private val itemNameDao: ItemNameDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(networkServices, itemNameDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
