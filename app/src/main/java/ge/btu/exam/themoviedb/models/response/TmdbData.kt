package ge.btu.exam.themoviedb.models.response

data class PopularMovies(
    val results: List<Result>
)

data class Result(
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String
)