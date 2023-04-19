package ir.hasin.mani.model.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieListResponse(
    @SerializedName("page") val page: String,
    @SerializedName("results") val results: ArrayList<Result>,
    @SerializedName("total_pages") val total_pages: String,
    @SerializedName("total_results") val total_results: String,
) : Parcelable


@Parcelize
data class Result(
    @SerializedName("adult") val adult: String,
    @SerializedName("backdrop_path") val backdrop_path: String,
    @SerializedName("genres") val genres: Genre,
    @SerializedName("id") val id: String,
    @SerializedName("original_language") val original_language: String,
    @SerializedName("original_title") val original_title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: String,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("title") val title: String,
    @SerializedName("video") val video: String,
    @SerializedName("vote_average") val vote_average: String,
    @SerializedName("vote_count") val vote_count: String,
) : Parcelable

@Parcelize
data class Genre(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
) : Parcelable