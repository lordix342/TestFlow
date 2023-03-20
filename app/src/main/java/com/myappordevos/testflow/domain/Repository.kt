package com.myappordevos.testflow.domain

import com.myappordevos.testflow.data.ApiService
import com.myappordevos.testflow.data.StandingsModels
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService): RepoGetter { //Use Case
    override fun getListCompetition(): Flow<StandingsModels> {
        return flow { emit(apiService.getStandingList()) }
            .flowOn(Dispatchers.IO)
    }
}

interface RepoGetter { //Repository
    fun getListCompetition() : Flow<StandingsModels>
}