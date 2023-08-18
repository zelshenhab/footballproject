package com.footballproject.ui.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.footballproject.data.remote.response.teams.Team
import com.footballproject.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@Immutable
data class ListViewState(
    val teams: PersistentList<Team> = persistentListOf(),
    val loading: Boolean = false,
    val error: String? = null,
)

sealed interface ListEvent {
    data class OnTeamClick(val id: Int) : ListEvent
    object OnCreate : ListEvent
    data class OnLoading(val isLoading: Boolean) : ListEvent
    data class OnError(val errorMessage: String?) : ListEvent
}

sealed interface ListAction {

    data class NavigateToTeam(val id: Int) : ListAction
}

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _state = MutableStateFlow<ListViewState>(ListViewState())
    val state: StateFlow<ListViewState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<ListAction?>()
    val action: SharedFlow<ListAction?>
        get() = _action.asSharedFlow()

    fun event(listEvent: ListEvent) {
        when (listEvent) {
            ListEvent.OnCreate -> onCreate()
            is ListEvent.OnTeamClick -> onTeamClick(listEvent)

            is ListEvent.OnError -> onError(listEvent)
            is ListEvent.OnLoading -> onLoading(listEvent)
        }
    }

    private fun onError(event: ListEvent.OnError) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    error = event.errorMessage
                )
            )
        }
    }

    private fun onLoading(event: ListEvent.OnLoading) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    loading = event.isLoading
                )
            )
        }
    }

    private fun onTeamClick(event: ListEvent.OnTeamClick) {
        viewModelScope.launch {
            _action.emit(ListAction.NavigateToTeam(event.id))
        }
    }

    private fun onCreate() {
        viewModelScope.launch {
            try {
                onLoading(ListEvent.OnLoading(true))
                _state.emit(
                    _state.value.copy(
                        teams = repository.getTeams().teams.toPersistentList(),
                    )
                )
                onError(ListEvent.OnError(null))
            } catch (throwable: Throwable) {
                onError(ListEvent.OnError(throwable.message))
            } finally {
                onLoading(ListEvent.OnLoading(false))
            }
        }
    }
}

