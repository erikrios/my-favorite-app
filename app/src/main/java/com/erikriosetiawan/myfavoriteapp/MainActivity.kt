package com.erikriosetiawan.myfavoriteapp

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erikriosetiawan.myfavoriteapp.adapters.FavoriteMovieAdapter
import com.erikriosetiawan.myfavoriteapp.db.FavoriteDbContract.FavoriteMovieEntry.Companion.COLUMN_OVERVIEW
import com.erikriosetiawan.myfavoriteapp.db.FavoriteDbContract.FavoriteMovieEntry.Companion.COLUMN_POSTER_PATH
import com.erikriosetiawan.myfavoriteapp.db.FavoriteDbContract.FavoriteMovieEntry.Companion.COLUMN_RELEASE_DATE
import com.erikriosetiawan.myfavoriteapp.db.FavoriteDbContract.FavoriteMovieEntry.Companion.COLUMN_TITLE
import com.erikriosetiawan.myfavoriteapp.db.FavoriteDbContract.FavoriteMovieEntry.Companion.COLUMN_VOTE_AVERAGE
import com.erikriosetiawan.myfavoriteapp.db.FavoriteDbContract.FavoriteMovieEntry.Companion.COLUMN_VOTE_COUNT
import com.erikriosetiawan.myfavoriteapp.db.FavoriteDbContract.FavoriteMovieEntry.Companion.TABLE_NAME
import com.erikriosetiawan.myfavoriteapp.models.FavoriteMovie


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val favoriteMovies: MutableList<FavoriteMovie> = mutableListOf()

    companion object {
        const val PROVIDER_NAME = "com.erikriosetiawan.cinemamovies.provider.FavoriteMovieProvider"
        const val URL = "content://$PROVIDER_NAME/$TABLE_NAME"
        val CONTENT_URI = Uri.parse(URL)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rv_favorite_movie)
        initData()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = FavoriteMovieAdapter(this, favoriteMovies)
    }

    @SuppressLint("Recycle")
    private fun initData() {
        val cursor = contentResolver.query(CONTENT_URI, null, null, null, null)!!
        if (cursor.moveToFirst()) {
            do {
                val favoriteMovie = FavoriteMovie()
                favoriteMovie.title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE))
                favoriteMovie.releaseDate =
                    cursor.getString(cursor.getColumnIndex(COLUMN_RELEASE_DATE))
                favoriteMovie.posterPath =
                    cursor.getString(cursor.getColumnIndex(COLUMN_POSTER_PATH))
                favoriteMovie.overview =
                    cursor.getString(cursor.getColumnIndex(COLUMN_OVERVIEW))
                favoriteMovie.voteCount =
                    cursor.getString(cursor.getColumnIndex(COLUMN_VOTE_COUNT))
                favoriteMovie.voteAverage =
                    cursor.getString(cursor.getColumnIndex(COLUMN_VOTE_AVERAGE))

                favoriteMovies.add(favoriteMovie)
            } while (cursor.moveToNext())
        }
        cursor.close()
    }
}
