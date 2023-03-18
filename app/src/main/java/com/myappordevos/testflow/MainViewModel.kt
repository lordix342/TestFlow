package com.myappordevos.testflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repoGetter: RepoGetter): ViewModel() {
    private val _resultApi : MutableStateFlow<StateApi> = MutableStateFlow(StateApi.Empty)
    val resultApi : StateFlow<StateApi>
        get() = _resultApi

    fun sendReq() = viewModelScope.launch {
        _resultApi.value = StateApi.Loading
        repoGetter.getListCompetition()
            .catch {except ->
                _resultApi.value = StateApi.ErrorLoading(except.stackTraceToString())
            }
            .collect {
                _resultApi.value =StateApi.Finished(it)
            }
    }
}