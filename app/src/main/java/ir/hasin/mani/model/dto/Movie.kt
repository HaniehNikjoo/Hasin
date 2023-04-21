package ir.hasin.mani.model.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieListResponse(
    @SerializedName("page") val page: String,
    @SerializedName("results") val movieResults: ArrayList<MovieResult>,
    @SerializedName("total_pages") val total_pages: String,
    @SerializedName("total_results") val total_results: String,
) : Parcelable


@Parcelize
data class MovieResult(
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val backdrop_path: String,
    @SerializedName("genres") val genres: ArrayList<Genre>,
    @SerializedName("id") val id: String,
    @SerializedName("original_language") val original_language: String,
    @SerializedName("original_title") val original_title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: String,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("title") val title: String,
    @SerializedName("video") val video: Boolean,
    @SerializedName("vote_average") val vote_average: String,
    @SerializedName("vote_count") val vote_count: String,
) : Parcelable

@Parcelize
data class Genre(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
) : Parcelable

@Parcelize
data class Collection(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("backdrop_path") val backdrop_path: String,
) : Parcelable

@Parcelize
data class Companies(
    @SerializedName("id") val id: String,
    @SerializedName("logo_path") val logo_path: String,
    @SerializedName("name") val name: String,
    @SerializedName("origin_country") val origin_country: String,
) : Parcelable

@Parcelize
data class Countries(
    @SerializedName("iso_3166_1") val iso_3166_1: String,
    @SerializedName("name") val name: String,
) : Parcelable

@Parcelize
data class Languages(
    @SerializedName("english_name") val english_name: String,
    @SerializedName("iso_639_1") val iso_639_1: String,
    @SerializedName("name") val name: String,
) : Parcelable

@Parcelize
data class MovieDetailResponse(
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val backdrop_path: String,
    @SerializedName("belongs_to_collection") val belongs_to_collection: Collection,
    @SerializedName("budget") val budget: String,
    @SerializedName("genres") val genres: ArrayList<Genre>,
    @SerializedName("homepage") val homepage: String,
    @SerializedName("id") val id: String,
    @SerializedName("imdb_id") val imdb_id: String,
    @SerializedName("original_language") val original_language: String,
    @SerializedName("original_title") val original_title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: String,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("production_companies") val production_companies: ArrayList<Companies>,
    @SerializedName("production_countries") val production_countries: ArrayList<Countries>,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("revenue") val revenue: String,
    @SerializedName("runtime") val runtime: String,
    @SerializedName("spoken_languages") val spoken_languages: ArrayList<Languages>,
    @SerializedName("status") val status: String,
    @SerializedName("tagline") val tagline: String,
    @SerializedName("title") val title: String,
    @SerializedName("video") val video: Boolean,
    @SerializedName("vote_average") val vote_average: String,
    @SerializedName("vote_count") val vote_count: String,
) : Parcelable