package ir.hasin.mani.model.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorResponse(
    @SerializedName("status_message") val status_message: String,
    @SerializedName("status_code") val status_code: Int,
) : Parcelable