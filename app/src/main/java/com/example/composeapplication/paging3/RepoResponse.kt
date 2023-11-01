package com.example.composeapplication.paging3

import com.example.composeapplication.paging3.Repo
import com.google.gson.annotations.SerializedName

class RepoResponse(
    @SerializedName("items") val items: List<Repo> = emptyList()
)
