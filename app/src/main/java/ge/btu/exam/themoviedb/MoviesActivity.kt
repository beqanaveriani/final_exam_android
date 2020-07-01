package ge.btu.exam.themoviedb

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import ge.btu.exam.themoviedb.external.ServiceBuilder
import ge.btu.exam.themoviedb.external.TmdbEndpoints
import ge.btu.exam.themoviedb.models.ItemModel
import ge.btu.exam.themoviedb.models.response.PopularMovies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    val items = ArrayList<ItemModel>()

    // TODO [SH] Initialize recycler view

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        init()
    }

    private fun init(){
        // TODO [SH] Add sign out button and linear layout manager

        loadExamples()
    }


    private fun addItem(itemData: ItemModel){
        items.add(itemData)
        // TODO [SH] notify adapter about change
    }

    private fun loadExamples(){
        val tmdbApi = ServiceBuilder.buildService(TmdbEndpoints::class.java)
        val response = tmdbApi.getMovies("05b74ca66d31909362cea3fbadb406cf")

        response.enqueue(object : Callback<PopularMovies> {
            override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
                val movies = response.body()!!.results
                for (movie in movies) {
                    addItem(ItemModel(movie.poster_path, movie.title, movie.overview, movie.release_date))
                }
            }
            override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
                Log.d("tmdbApCall", "Error occurred while getting data from tmdbApi")
            }
        })

    }
}
