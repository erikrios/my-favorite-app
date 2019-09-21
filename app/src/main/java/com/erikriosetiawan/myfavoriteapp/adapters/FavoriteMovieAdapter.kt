package com.erikriosetiawan.myfavoriteapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erikriosetiawan.myfavoriteapp.DetailActivity
import com.erikriosetiawan.myfavoriteapp.R
import com.erikriosetiawan.myfavoriteapp.models.FavoriteMovie
import com.squareup.picasso.Picasso

class FavoriteMovieAdapter(
    private val context: Context,
    private val favoriteMovies: List<FavoriteMovie>
) :
    RecyclerView.Adapter<FavoriteMovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_favorite_movie,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = favoriteMovies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(favoriteMovies[position]) {
            val dataIntent = Intent(context, DetailActivity::class.java)
            dataIntent.putExtra(DetailActivity.MOVIE_KEY, favoriteMovies[position])
            context.startActivity(dataIntent)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imgPoster: ImageView = view.findViewById(R.id.img_poster)
        private val tvTitle: TextView = view.findViewById(R.id.tv_title)
        private val tvOverview: TextView = view.findViewById(R.id.tv_overview)
        private val tvReleaseDate: TextView = view.findViewById(R.id.tv_release_date)

        fun bindItem(favoriteMovie: FavoriteMovie, listener: (FavoriteMovie) -> Unit) {
            Picasso.get().load("https://image.tmdb.org/t/p/w185${favoriteMovie.posterPath}")
                .into(imgPoster)
            tvTitle.text = favoriteMovie.title
            tvOverview.text = favoriteMovie.overview
            tvReleaseDate.text = favoriteMovie.releaseDate

            itemView.setOnClickListener { listener(favoriteMovie) }
        }
    }
}