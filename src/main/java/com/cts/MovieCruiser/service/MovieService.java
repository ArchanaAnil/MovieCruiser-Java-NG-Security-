package com.cts.MovieCruiser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.MovieCruiser.Movie;
import com.cts.MovieCruiser.repository.MovieRepository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MovieService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieService.class);

	@Autowired
	MovieRepository movieRepository;

	@Transactional
	public List<Movie> getAllMovies() {

		return movieRepository.getAllMovies();
	}

	@Transactional
	public Movie findById(int MovId) {
		return movieRepository.findById(MovId).get();
	}

	@Transactional
	public List<Movie> findAllMoviesCustomer() {
		return movieRepository.findByActive("Yes");

	}

	@Transactional
	public void saveMovie(Movie movie) {

		movieRepository.save(movie);

	}

	@Transactional
	public Movie findByName(String title) {

		return movieRepository.findByTitle(title);

	}
	
	

	 
}
