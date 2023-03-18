package com.myappordevos.testflow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService): RepoGetter {
    override fun getListCompetition(): Flow<StandingsModels> {
        return flow { emit(apiService.getStandingList()) }
            .flowOn(Dispatchers.IO)
    }
}

interface RepoGetter {
    fun getListCompetition() : Flow<StandingsModels>
}