package ir.hasin.mani.model.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class Resource<out T>(
    @SerializedName("status") val status: Status,
    @SerializedName("data") val data: T? = null,
    @SerializedName("message") val message: String? = null
) {
    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

    }
}

@Parcelize
enum class Status : Parcelable {
    @SerializedName("SUCCESS")
    SUCCESS,

    @SerializedName("ERROR")
    ERROR,

    @SerializedName("LOADING")
    LOADING
}