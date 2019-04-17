package mobileapp.com.newtestcandidate.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import org.json.JSONObject


data class JsonListModel(


        @SerializedName("data")
        val dataList: List<JsonObject> = listOf()

)


