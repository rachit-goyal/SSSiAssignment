package com.learn.sssiassignment.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learn.sssiassignment.domain.repository.RepositoryInterface
import com.learn.sssiassignment.data.remote.models.UserData
import com.learn.sssiassignment.data.local.UserLocalModel
import com.learn.sssiassignment.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
created by Rachit on 3/27/2024.
 */
@HiltViewModel
class UserDataViewModel @Inject constructor(private val repositoryInterface: RepositoryInterface) :
    ViewModel() {

    private val _data = MutableLiveData<Resource<UserData>>()
    val data: LiveData<Resource<UserData>> = _data
    private var _localData = MutableLiveData<List<UserLocalModel>>()
    val localData: LiveData<List<UserLocalModel>> = _localData

    init {
        viewModelScope.launch {
            when (val result = repositoryInterface.getData()) {
                is Resource.Error -> {
                    _data.value = Resource.Error(result.error)
                }

                Resource.Loading -> {
                    _data.value = Resource.Loading

                }

                is Resource.Success -> {
                    _data.value = Resource.Success(result.data)

                }
            }

        }
    }

    fun insertData(userLocalModel: UserLocalModel) {
        viewModelScope.launch {
            repositoryInterface.insertUser(userLocalModel)
        }
    }

    fun getLocalData() {

        viewModelScope.launch {
            val result = repositoryInterface.getUsers()
            result.collect{

                _localData.value=it
            }

        }

    }
}

