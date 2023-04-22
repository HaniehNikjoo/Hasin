package ir.hasin.mani.util

import com.google.gson.Gson
import ir.hasin.mani.model.dto.ErrorResponse
import retrofit2.HttpException

object Utils {
    fun getMessageError(it: Throwable): String {
        val errorResponse = Gson().fromJson(
            (it as? HttpException)?.response()?.errorBody()?.string(),
            ErrorResponse::class.java
        )
        return errorResponse?.status_message ?: it.message.toString()
    }
}