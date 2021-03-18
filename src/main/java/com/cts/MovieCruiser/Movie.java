package com.cts.MovieCruiser;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="movie")
public class Movie {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name="id")
	int movie_id;
	
	@Column(name="title")
	String title;
	
	@Column(name="box_office")
	BigInteger box_office;
	
	@Column(name="active_status")
	String active;
	
	@Column(name="genre")
	String genre;
	
	@Column(name="hasTeaser")
	String hasTeaser;
	
	@Column(name="date_of_launch")
	Date dateOfLaunch;
	
	@OneToOne(mappedBy = "movies")
	private Favorites favorite;
	
	
	public Movie(int l, String title, BigInteger box_office, String active, Date dateOfLaunch, String genre,
			String hasTeaser) {
		super();
		this.movie_id = l;
		this.title = title;
		this.box_office = box_office;
		this.active = active;
		this.dateOfLaunch = dateOfLaunch;
		this.genre = genre;
		this.hasTeaser = hasTeaser;
	}

	
	public Movie() {
		
	}


	public int getMovie_id() {
		return movie_id;
	}


	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}


	public String getTitle() {
		return title;
	}

	public BigInteger getBox_office() {
		return box_office;
	}

	public String getActive() {
		return active;
	}

	public Date getDateOfLaunch() {
		return dateOfLaunch;
	}

	public String getGenre() {
		return genre;
	}

	public String getHasTeaser() {
		return hasTeaser;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setBox_office(BigInteger box_office) {
		this.box_office = box_office;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public void setDateOfLaunch(Date dateOfLaunch) {
		this.dateOfLaunch = dateOfLaunch;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setHasTeaser(String hasTeaser) {
		this.hasTeaser = hasTeaser;
	}


	@Override
	public String toString() {
		return "Movie [movie_id=" + movie_id + ", title=" + title + ", box_office=" + box_office + ", active=" + active
				+ ", dateOfLaunch=" + dateOfLaunch + ", Genre=" + genre + ", hasTeaser=" + hasTeaser + "]";
	}
	
	
	
	
}
