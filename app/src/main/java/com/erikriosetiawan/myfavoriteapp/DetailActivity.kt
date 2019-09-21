package com.erikriosetiawan.myfavoriteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.erikriosetiawan.myfavoriteapp.models.FavoriteMovie
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE_KEY = "Bva9LL2W2s"
    }

    private lateinit var imgPoster: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvOverview: TextView
    private lateinit var tvReleaseDate: TextView

    private lateinit var favoriteMovie: FavoriteMovie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initView()
        favoriteMovie = getDataIntent()
        bindView()
    }

    private fun initView() {
        imgPoster = findViewById(R.id.img_poster)
        tvTitle = findViewById(R.id.tv_title)
        tvOverview = findViewById(R.id.tv_overview)
        tvReleaseDate = findViewById(R.id.tv_release_date)
    }

    private fun getDataIntent(): FavoriteMovie = intent.getParcelableExtra(MOVIE_KEY)

    private fun bindView() {
        Picasso.get().load("https://image.tmdb.org/t/p/w185${favoriteMovie.posterPath}")
            .into(imgPoster)
        tvTitle.text = favoriteMovie.title
        tvOverview.text = favoriteMovie.overview
        tvReleaseDate.text = favoriteMovie.releaseDate
    }
}
