package com.myappordevos.testflow.data

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

interface ApiService { // Storage
    @GET("competitions/?areas=2077")
    suspend fun getStandingList() : StandingsModels
}

data class StandingsModels( // Model Storage &  another
    @SerializedName("competitions")
    val competitions: List<Competition>
)

data class Competition(
    @SerializedName("emblem")
    val emblem: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

sealed class StateApi {
    object Loading:StateApi()
    object Empty:StateApi()
    class Finished(val result:StandingsModels):StateApi()
    data class ErrorLoading(val message:String) : StateApi()
}