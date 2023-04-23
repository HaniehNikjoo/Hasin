package ir.hasin.mani.model.datasource

import ir.hasin.mani.model.dto.*

class MockApiImplementation : MovieWebApiDatasource {
    override suspend fun getMovieList(page: Int): MovieListResponse {
        val list = ArrayList<MovieResult>()
        val item = MovieResult(
            adult = true,
            backdrop_path = "/rPSJAElGxOTko1zK6uIlYnTMFxN.jpg",
            genres = arrayListOf(Genre(id = "80", name = "Crime")),
            id = "1104040",
            original_language = "",
            original_title = "",
            overview = "",
            popularity = "",
            poster_path = "/rPSJAElGxOTko1zK6uIlYnTMFxN.jpg",
            release_date = "",
            title = "",
            video = false,
            vote_average = "",
            vote_count = ""
        )
        list.add(item)
        list.add(item)
        list.add(item)
        return MovieListResponse(
            page = "1", total_pages = "10", total_results = "10", movieResults = list
        )
    }

    override suspend fun getMovieDetail(movie_id: String): MovieDetailResponse {
        return MovieDetailResponse(
            adult = true,
            backdrop_path = "/rPSJAElGxOTko1zK6uIlYnTMFxN.jpg",
            genres = arrayListOf(Genre(id = "80", name = "Crime")),
            id = "1104040",
            original_language = "",
            original_title = "",
            overview = "",
            popularity = "",
            poster_path = "/rPSJAElGxOTko1zK6uIlYnTMFxN.jpg",
            release_date = "",
            title = "",
            video = false,
            vote_average = "",
            vote_count = "",
            belongs_to_collection = Collection("", "", "", ""),
            budget = "",
            homepage = "",
            imdb_id = "",
            production_companies = arrayListOf(
                Companies(
                    "", name = "", logo_path = "", origin_country = ""
                )
            ),
            production_countries = arrayListOf(Countries("", "")),
            revenue = "",
            runtime = "",
            spoken_languages = arrayListOf(Languages("", "", "")),
            status = "",
            tagline = ""
        )
    }
}