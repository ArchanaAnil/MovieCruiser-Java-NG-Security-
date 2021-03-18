package com.cts.MovieCruiser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "favorites")
public class Favorites {
	
	@Id
	@Column(name = "fav_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "user_id")
	private int favUsId;
	
	@OneToOne
	@JoinColumn(name = "movie_id")
	private Movie movies;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFavUsId() {
		return favUsId;
	}
	public void setFavUsId(int favUsId) {
		this.favUsId = favUsId;
	}
	public Movie getMovies() {
		return movies;
	}
	public void setMovies(Movie movies) {
		this.movies = movies;
	}
	
	
	

}



