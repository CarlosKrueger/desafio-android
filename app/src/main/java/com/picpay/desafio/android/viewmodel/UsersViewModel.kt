package com.picpay.desafio.android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.domain.models.User
import com.picpay.desafio.android.data.repositories.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class UsersViewModel(private val usersRepository: UsersRepository): ViewModel() {

    val users: LiveData<List<User>> = usersRepository.users

    private val _error: MutableLiveData<Int> = MutableLiveData()
    val error: LiveData<Int> = _error

    init {
        updateUserList()
    }

    private fun updateUserList() = viewModelScope.launch(Dispatchers.IO) {
        try {
            usersRepository.fetchUsers()
        } catch (npe: NullPointerException) {
           _error.postValue(R.string.network_error)
        } catch (ex: Exception) {
            _error.postValue(R.string.error)
        }
    }

    fun errorShown() = _error.postValue(null)
}
