package com.footballproject.ui.screen.team

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.footballproject.data.remote.response.team.TeamResponse
import com.footballproject.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.annotation.concurrent.Immutable
import javax.inject.Inject


@Immutable
data class TeamViewState(
    val team: TeamResponse? = null,
    val loading: Boolean = true,
    val error: String? = null,
)

sealed interface TeamEvent {
    data class OnCreate(val id: Int) : TeamEvent

    data class OnLoading(val isLoading: Boolean) : TeamEvent
    data class OnError(val errorMessage: String?) : TeamEvent
}

@HiltViewModel
class TeamViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _state = MutableStateFlow<TeamViewState>(TeamViewState().copy())
    val state: StateFlow<TeamViewState>
        get() = _state.asStateFlow()

    fun event(teamEvent: TeamEvent) {
        when (teamEvent) {
            is TeamEvent.OnCreate -> onCreate(teamEvent)
            is TeamEvent.OnError -> onError(teamEvent)
            is TeamEvent.OnLoading -> onLoading(teamEvent)
        }
    }

    private fun onCreate(event: TeamEvent.OnCreate) {
        viewModelScope.launch {
            try {
                onLoading(TeamEvent.OnLoading(true))
                _state.emit(
                    _state.value.copy(
                        team = repository.getTeamById(event.id)
                    )
                )
            } catch (throwable: Throwable) {
                onError(TeamEvent.OnError(throwable.message))
            } finally {
                onLoading(TeamEvent.OnLoading(false))
            }
        }
    }

    private fun onError(event: TeamEvent.OnError) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    error = event.errorMessage
                )
            )
        }
    }

    private fun onLoading(event: TeamEvent.OnLoading) {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    loading = event.isLoading
                )
            )
        }
    }
}
